package co.com.cybersoft.man.services.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import co.com.cybersoft.purchase.tables.core.domain.ItemPurchaseHistoryDetails;
import co.com.cybersoft.purchase.tables.persistence.domain.Item;
import co.com.cybersoft.purchase.tables.persistence.domain.ItemPurchaseHistory;
import co.com.cybersoft.purchase.tables.web.domain.itemPurchaseHistory.ItemPurchaseHistoryInfo;

/**
 * Custom services regarding items: get last purchase price of an item,
 * send an item verification message, etc.
 * @author daniel
 *
 */
public class ItemManServiceImpl implements ItemManService{

	@Autowired
	private MongoOperations mongo;
	
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

}