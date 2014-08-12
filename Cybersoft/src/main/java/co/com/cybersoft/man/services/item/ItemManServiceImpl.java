package co.com.cybersoft.man.services.item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import co.com.cybersoft.docs.web.domain.requisition.RequisitionBodyInfo;
import co.com.cybersoft.docs.web.domain.requisition.RequisitionInfo;
import co.com.cybersoft.tables.core.domain.ItemPurchaseHistoryDetails;
import co.com.cybersoft.tables.persistence.domain.BusinessRules;
import co.com.cybersoft.tables.persistence.domain.Item;
import co.com.cybersoft.tables.persistence.domain.ItemPurchaseHistory;
import co.com.cybersoft.tables.persistence.domain.User;
import co.com.cybersoft.tables.persistence.repository.businessRules.BusinessRulesRepository;
import co.com.cybersoft.tables.persistence.repository.user.UserRepository;
import co.com.cybersoft.tables.web.domain.itemPurchaseHistory.ItemPurchaseHistoryInfo;

/**
 * Custom services regarding items: get last purchase price of an item,
 * send an item verification message, etc.
 * @author daniel
 *
 */
public class ItemManServiceImpl implements ItemManService{

	@Autowired
	private MongoOperations mongo;
	
	@Autowired
	private BusinessRulesRepository businessRulesRepo;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public ItemPurchaseHistoryInfo getItemLastPurchasePriceByItemCode(String item) throws Exception{
		
		Query query=new Query();
		query.addCriteria(Criteria.where("item").is(item)).with(new Sort(Sort.Direction.DESC,"purchaseDate"));
		
		List<ItemPurchaseHistory> history = mongo.find(query, ItemPurchaseHistory.class);
		
		if (!history.isEmpty()){
			ItemPurchaseHistoryInfo historyInfo = new ItemPurchaseHistoryInfo();
			ItemPurchaseHistoryDetails historyDetails = new ItemPurchaseHistoryDetails();
			historyDetails = historyDetails.toItemPurchaseHistoryDetails(history.get(0));
			historyInfo.setPurchaseDate(historyDetails.getPurchaseDate());
			historyInfo.setLocalCurrencyValue(historyDetails.getLocalCurrencyValue());
			historyInfo.setForeignCurrencyValue(historyDetails.getForeignCurrencyValue());
			
			return historyInfo;
		}
		
		return null;
	}

	@Override
	public ItemPurchaseHistoryInfo getItemLastPurchasePriceByDescription(
			String description) throws Exception {
		Criteria criteria=new Criteria("description");		
		criteria.is(description);
		Query query=new Query();
		query.addCriteria(criteria);
		Item item = mongo.findOne(query, Item.class);
		
		return getItemLastPurchasePriceByItemCode(item.getCode());
	}

	@Override
	public void checkAndSendVerificationMessage(String requestingUser,
			RequisitionInfo requisition) throws Exception {
		List<BusinessRules> all = businessRulesRepo.findAll();
		List<String> items=new ArrayList<String>();
		
		//Add the items for verification (those that doesn't have any code)
		List<RequisitionBodyInfo> requisitionBodyList = requisition.getRequisitionBodyList();
		if (requisition.getCheckDocument()!=null && requisition.getCheckDocument())
			for (RequisitionBodyInfo requisitionBody : requisitionBodyList) {
				if (requisitionBody.getItem()== null || requisitionBody.getItem().equals("")){
					items.add(requisitionBody.getItemDescription());
				}
			}
		
		//Set and send the message, if it's the case
		if (!all.isEmpty()&&!items.isEmpty()){
			BusinessRules businessRules = all.get(0);
			String chiefAdminUser = businessRules.getChiefAdminUser();
			User user = userRepository.findByUser(chiefAdminUser);
			User reqUser = userRepository.findByUser(requestingUser);
			new Thread(new ItemVerificationSender(items,user,reqUser)).start();
		}
	}

	/**
	 * Checks whether the requisition is ready, i.e, whether it has every
	 * piece of information needed to continue the process
	 */
	@Override
	public RequisitionInfo checkRequisition(RequisitionInfo requisitionInfo)
			throws Exception {
		if (requisitionInfo.getReady()){
			List<RequisitionBodyInfo> requisitionBodyList = requisitionInfo.getRequisitionBodyList();
			for (RequisitionBodyInfo requisitionBody : requisitionBodyList) {
				if (requisitionBody.getItem()== null || requisitionBody.getItem().equals("")){
					requisitionInfo.setCheckDocument(true);
					requisitionInfo.setReady(false);
					break;
				}
			}
		}
		return requisitionInfo;
	}


}
