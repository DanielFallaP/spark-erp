package co.com.cybersoft.man.services.quotation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.cybersoft.docs.events.purchaseOrder.SavePurchaseOrderEvent;
import co.com.cybersoft.docs.persistence.services.purchaseOrder.PurchaseOrderPersistenceService;
import co.com.cybersoft.docs.web.domain.purchaseOrder.PurchaseOrderBodyInfo;
import co.com.cybersoft.docs.web.domain.purchaseOrder.PurchaseOrderInfo;
import co.com.cybersoft.docs.web.domain.quotation.QuotationBodyInfo;
import co.com.cybersoft.docs.web.domain.quotation.QuotationInfo;
import co.com.cybersoft.man.services.exchangeRate.ExchangeRateManService;
import co.com.cybersoft.tables.persistence.domain.ThirdParty;
import co.com.cybersoft.tables.persistence.domain.User;
import co.com.cybersoft.tables.persistence.repository.user.UserRepository;
import co.com.cybersoft.util.CyberUtils;

public class QuotationManServiceImpl implements QuotationManService{

	@Autowired
	private ExchangeRateManService exchangeRateService;
	
	@Autowired
	private PurchaseOrderPersistenceService purchaseOrderService;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@Override
	public void checkQuotationBody(QuotationBodyInfo quotationBodyInfo) throws Exception{
		Double todaysRate = exchangeRateService.getTodayLocalToForeignExchangeRate();
		
		if (quotationBodyInfo.getForeignCurrencyUnitValue()!=null){
			quotationBodyInfo.setForeignCurrencyTotalValue(quotationBodyInfo.getForeignCurrencyUnitValue()*quotationBodyInfo.getQuantity());
			quotationBodyInfo.setLocalCurrencyTotalValue(quotationBodyInfo.getForeignCurrencyUnitValue()*quotationBodyInfo.getQuantity()*todaysRate);
			quotationBodyInfo.setLocalCurrencyUnitValue(quotationBodyInfo.getForeignCurrencyUnitValue()*todaysRate);
		}
		else if (quotationBodyInfo.getLocalCurrencyUnitValue()!=null){
			quotationBodyInfo.setForeignCurrencyTotalValue(quotationBodyInfo.getLocalCurrencyUnitValue()*quotationBodyInfo.getQuantity()/todaysRate);
			quotationBodyInfo.setLocalCurrencyTotalValue(quotationBodyInfo.getLocalCurrencyUnitValue()*quotationBodyInfo.getQuantity());
			quotationBodyInfo.setForeignCurrencyUnitValue(quotationBodyInfo.getLocalCurrencyUnitValue()/todaysRate);
		}
		
	}

	@Override
	public void generatePurchaseOrders(QuotationInfo quotationInfo) throws Exception{

		if (quotationInfo.getReady()){
			List<QuotationBodyInfo> bodyList = quotationInfo.getQuotationBodyList();
			List<RequiredItem> acceptedItems = new ArrayList<>();
			List<QuotationSupplier> acceptedSuppliers = new ArrayList<>();
			for (QuotationBodyInfo quotationBodyInfo : bodyList) {
				RequiredItem requiredItem = new RequiredItem(quotationBodyInfo.getItem(), quotationBodyInfo.getItemDescription(), quotationBodyInfo.getUnit(), quotationBodyInfo.getQuotedQuantity(), quotationBodyInfo.getQuantity());
				RequiredItem acceptedItem = new RequiredItem();
				BeanUtils.copyProperties(requiredItem, acceptedItem);
				
				if (quotationBodyInfo.getAccepted() && acceptedItems.contains(requiredItem)){
					for (RequiredItem accepted : acceptedItems) {
						if (accepted.equals(requiredItem)){
							accepted.setAcceptedQuantity(accepted.getAcceptedQuantity()+requiredItem.getAcceptedQuantity());
						}
					}
				}
				else if (quotationBodyInfo.getAccepted()){
					acceptedItems.add(requiredItem);
					QuotationSupplier acceptedSupplier = new QuotationSupplier();
					acceptedSupplier.setSupplier(quotationBodyInfo.getThirdParty());
					if (acceptedSuppliers.contains(acceptedSupplier)){
						for (QuotationSupplier accepted : acceptedSuppliers) {
							if (accepted.equals(acceptedSupplier)){
								accepted.getItems().add(acceptedItem);
							}
						}
					}
					else{
						acceptedSuppliers.add(acceptedSupplier);
						acceptedSupplier.getItems().add(acceptedItem);
					}
				}
			}
			
			boolean complete=true;
			for (RequiredItem requiredItem : acceptedItems) {
				if (!requiredItem.getAcceptedQuantity().equals(requiredItem.getRequiredQuantity())){
					complete=false;
					break;
				}
			}
			if (complete){
				try {
					
					Double todaysRate = exchangeRateService.getTodayLocalToForeignExchangeRate();
					
					for (QuotationSupplier acceptedSupplier : acceptedSuppliers) {
						PurchaseOrderInfo purchaseOrderInfo = new PurchaseOrderInfo();
						purchaseOrderInfo.setCity(quotationInfo.getCity());
						purchaseOrderInfo.setThirdParty(acceptedSupplier.getSupplier());
						purchaseOrderInfo.setQuotationNumber(quotationInfo.getStringId());
						purchaseOrderInfo.setActive(Boolean.TRUE);
						purchaseOrderInfo.setReady(Boolean.FALSE);
						purchaseOrderInfo.set_toSave(Boolean.TRUE);
						purchaseOrderInfo.setCreatedBy(CyberUtils.defaultCreatingUser);
						purchaseOrderInfo.setDate(quotationInfo.getDate());
						purchaseOrderInfo.setExchangeRate(todaysRate);
						purchaseOrderInfo.setDateOfCreation(new Date());
//					purchaseOrderInfo.setId(UUID.randomUUID().toString().replace("-", "").substring(8));
						
						ArrayList<PurchaseOrderBodyInfo> purchaseOrderBodyList = new ArrayList<>();
						List<RequiredItem> items = acceptedSupplier.getItems();
						for (RequiredItem requiredItem : items) {
							PurchaseOrderBodyInfo bodyInfo = new PurchaseOrderBodyInfo();
							bodyInfo.setId(UUID.randomUUID().toString());
							bodyInfo.setItem(requiredItem.getItemCode());
							bodyInfo.setItemDescription(requiredItem.getItemDescription());
							bodyInfo.setUnit(requiredItem.getUnit());
							purchaseOrderBodyList.add(bodyInfo);						
						}
						purchaseOrderInfo.setPurchaseOrderBodyList(purchaseOrderBodyList);
						
						purchaseOrderService.savePurchaseOrder(new SavePurchaseOrderEvent(purchaseOrderInfo));					
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			}
			else{
				quotationInfo.setReady(Boolean.FALSE);
				throw new Exception("label.incompleteQuotation");
			}
		}
	}

	@Override
	public void processQuotation(String requestingUser, QuotationInfo quotationInfo) throws Exception {
		generatePurchaseOrders(quotationInfo);
		sendNotificationsToThirdParties(requestingUser, quotationInfo);
	}

	@Override
	public void sendNotificationsToThirdParties(String requestingUser, QuotationInfo quotationInfo)throws Exception {
		if (quotationInfo.getSendNotifications()){
			List<QuotationBodyInfo> bodyList = quotationInfo.getQuotationBodyList();
			List<RequiredItem> quotationItems = new ArrayList<>();
			List<QuotationSupplier> biddingSuppliers = new ArrayList<>();
			for (QuotationBodyInfo quotationBodyInfo : bodyList) {
				RequiredItem requiredItem = new RequiredItem(quotationBodyInfo.getItem(), quotationBodyInfo.getItemDescription(), quotationBodyInfo.getUnit(), quotationBodyInfo.getQuotedQuantity(), quotationBodyInfo.getQuantity());
				RequiredItem quotationItem = new RequiredItem();
				BeanUtils.copyProperties(requiredItem, quotationItem);
				
				if (quotationItems.contains(requiredItem)){
					for (RequiredItem accepted : quotationItems) {
						if (accepted.equals(requiredItem)){
							accepted.setAcceptedQuantity(accepted.getAcceptedQuantity()+requiredItem.getAcceptedQuantity());
						}
					}
				}
				else {
					quotationItems.add(requiredItem);
					QuotationSupplier biddingSupplier = new QuotationSupplier();
					biddingSupplier.setSupplier(quotationBodyInfo.getThirdParty());
					if (biddingSuppliers.contains(biddingSupplier)){
						for (QuotationSupplier accepted : biddingSuppliers) {
							if (accepted.equals(biddingSupplier)){
								accepted.getItems().add(quotationItem);
							}
						}
					}
					else{
						biddingSuppliers.add(biddingSupplier);
						biddingSupplier.getItems().add(quotationItem);
					}
				}
			}
			for (QuotationSupplier quotationSupplier : biddingSuppliers) {
				User reqUser = userRepo.findByUser(requestingUser);
				new Thread(new QuotationRequestSender(reqUser, quotationSupplier)).start();
			}
		}
	}

}