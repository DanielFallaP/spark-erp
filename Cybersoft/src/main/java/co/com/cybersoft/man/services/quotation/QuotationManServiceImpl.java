package co.com.cybersoft.man.services.quotation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.cybersoft.docs.persistence.services.purchaseOrder.PurchaseOrderPersistenceService;
import co.com.cybersoft.docs.web.domain.quotation.QuotationBodyInfo;
import co.com.cybersoft.docs.web.domain.quotation.QuotationInfo;
import co.com.cybersoft.man.services.exchangeRate.ExchangeRateManService;

public class QuotationManServiceImpl implements QuotationManService{

	@Autowired
	private ExchangeRateManService exchangeRateService;
	
	@Autowired
	private PurchaseOrderPersistenceService purchaseOrderService;
	
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
			List<AcceptedSupplier> acceptedSuppliers = new ArrayList<>();
			for (QuotationBodyInfo quotationBodyInfo : bodyList) {
				RequiredItem requiredItem = new RequiredItem(quotationBodyInfo.getItem(), quotationBodyInfo.getQuotedQuantity(), quotationBodyInfo.getQuantity());
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
					AcceptedSupplier acceptedSupplier = new AcceptedSupplier();
					acceptedSupplier.setSupplier(quotationBodyInfo.getThirdParty());
					if (acceptedSuppliers.contains(acceptedSupplier)){
						for (AcceptedSupplier accepted : acceptedSuppliers) {
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
				for (AcceptedSupplier acceptedSupplier : acceptedSuppliers) {
					System.out.println("ACCEPTED:"+acceptedSupplier.getSupplier()+" "+acceptedSupplier.getItems().size());
				}
			}
			else{
				quotationInfo.setReady(Boolean.FALSE);
				throw new Exception("label.incompleteQuotation");
			}
		}
	}

}
