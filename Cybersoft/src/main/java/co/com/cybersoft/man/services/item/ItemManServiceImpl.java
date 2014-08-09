package co.com.cybersoft.man.services.item;

import java.util.ArrayList;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import co.com.cybersoft.docs.web.controller.requisition.RequisitionController;
import co.com.cybersoft.docs.web.domain.requisition.RequisitionBodyInfo;
import co.com.cybersoft.docs.web.domain.requisition.RequisitionInfo;
import co.com.cybersoft.man.mail.MailSender;
import co.com.cybersoft.tables.core.domain.ItemPurchaseHistoryDetails;
import co.com.cybersoft.tables.persistence.domain.BusinessRules;
import co.com.cybersoft.tables.persistence.domain.Item;
import co.com.cybersoft.tables.persistence.domain.ItemPurchaseHistory;
import co.com.cybersoft.tables.persistence.domain.User;
import co.com.cybersoft.tables.persistence.repository.businessRules.BusinessRulesRepository;
import co.com.cybersoft.tables.persistence.repository.user.UserRepository;
import co.com.cybersoft.tables.web.domain.itemPurchaseHistory.ItemPurchaseHistoryInfo;
import co.com.cybersoft.util.CyberUtils;

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
	
	private static final Logger LOG = LoggerFactory.getLogger(RequisitionController.class);
	
	@Override
	public ItemPurchaseHistoryInfo getItemLastPurchasePriceByItemCode(String item) throws Exception{
		Criteria criteria=new Criteria("item");		
		criteria.is(item);
		
		Query query=new Query();
		query.addCriteria(criteria).with(new Sort(Sort.Direction.DESC,"purchaseDate"));
		
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
		for (RequisitionBodyInfo requisitionBody : requisitionBodyList) {
			if (requisitionBody.getItem()== null || requisitionBody.getItem().equals("")){
				items.add(requisitionBody.getItemDescription());
			}
		}
		
		//Set and send the message, if it's the case
		if (!all.isEmpty()&&!items.isEmpty()&&requisition.getReady()){
			BusinessRules businessRules = all.get(0);
			String chiefAdminUser = businessRules.getChiefAdminUser();
			User user = userRepository.findByUser(chiefAdminUser);
			User reqUser = userRepository.findByUser(requestingUser);
			StringTemplate template = new StringTemplate(CyberUtils.itemMessageTemplateDir);
			template.setAttribute("user", user.getFirstNames());
			String itemList="";
			for (String item : items) {
				itemList+="		"+item+".\n";
			}
			template.setAttribute("itemList", itemList);
			template.setAttribute("requestingUser", reqUser.getUserName()+" "+reqUser.getLastName());
			template.setAttribute("requestingUserMail", reqUser.getEmail());
		
			new Thread(new MailSender(new CyberUtils().getSimpleMessage(user.getEmail(), reqUser.getEmail(), "Verificación de items", template.toString()))).start();
			
			LOG.debug("Sent email message to "+chiefAdminUser+"requested by "+reqUser.getUser()+"regarding item verification");
		}
	}


}
