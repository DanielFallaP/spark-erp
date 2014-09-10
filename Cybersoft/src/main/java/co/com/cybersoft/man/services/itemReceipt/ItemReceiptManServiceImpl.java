package co.com.cybersoft.man.services.itemReceipt;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import co.com.cybersoft.docs.persistence.domain.PurchaseOrder;
import co.com.cybersoft.docs.persistence.domain.PurchaseOrderBody;
import co.com.cybersoft.docs.persistence.repository.purchaseOrder.PurchaseOrderRepository;
import co.com.cybersoft.docs.web.domain.itemReceipt.ItemReceiptBodyInfo;
import co.com.cybersoft.docs.web.domain.itemReceipt.ItemReceiptInfo;
import co.com.cybersoft.tables.persistence.domain.StockBalance;
import co.com.cybersoft.tables.persistence.repository.stockBalance.StockBalanceRepository;

public class ItemReceiptManServiceImpl implements ItemReceiptManService{
	
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepo;
	
	@Autowired
	private StockBalanceRepository stockBalanceRepo;
	
	@Autowired
	private MongoOperations mongo;
	
	@Override
	public ItemReceiptInfo processItemReceipt(ItemReceiptInfo itemReceiptInfo)
			throws Exception {
		if (itemReceiptInfo.getReady()){
			PurchaseOrder purchaseOrder = purchaseOrderRepo.findByNumericId(Long.parseLong(itemReceiptInfo.getPurchaseOrderNumber()));
			List<ItemReceiptBodyInfo> itemReceiptBodyList = itemReceiptInfo.getItemReceiptBodyList();
			List<PurchaseOrderBody> purchaseOrderBody = purchaseOrder.getPurchaseOrderBodyEntityList();
			for (PurchaseOrderBody purchaseOrderBodyRecord : purchaseOrderBody) {
				for (ItemReceiptBodyInfo itemReceiptBodyInfo : itemReceiptBodyList) {
					if (itemReceiptBodyInfo.getItem().trim().equals(purchaseOrderBodyRecord.getItem()))
						purchaseOrderBodyRecord.setReceivedQuantity(itemReceiptBodyInfo.getOrderedQuantity());
				}
				Criteria itemCriteria = new Criteria("item");
				itemCriteria.is(purchaseOrderBodyRecord.getItem());
				Criteria warehouseCriteria = new Criteria("warehouse");
				warehouseCriteria.is(purchaseOrder.getReceivingWarehouse());
				Criteria andOperator = itemCriteria.andOperator(warehouseCriteria);
				Query query = new Query();
				query.addCriteria(andOperator);
				StockBalance stockBalance = mongo.findOne(query, StockBalance.class);
				if (stockBalance==null){
					stockBalance = new StockBalance();
					stockBalance.setActive(true);
					stockBalance.setCreatedBy("admin");
					stockBalance.setDateOfCreation(new Date());
					stockBalance.setDateOfModification(new Date());
					stockBalance.setExistingQuantity(purchaseOrderBodyRecord.getReceivedQuantity());
					stockBalance.setForeignCurrencyAvertageValue(purchaseOrderBodyRecord.getForeignCurrencyTotalValue()/purchaseOrderBodyRecord.getReceivedQuantity());
					stockBalance.setLocalCurrencyAvertageValue(purchaseOrderBodyRecord.getLocalCurrencyTotalValue()/purchaseOrderBodyRecord.getReceivedQuantity());
					stockBalance.setForeignCurrencyLastValue(purchaseOrderBodyRecord.getForeignCurrencyUnitValue());
					stockBalance.setLocalCurrencyLastValue(purchaseOrderBodyRecord.getLocalCurrencyUnitValue());
					stockBalance.setItemCondition("1");
					stockBalance.setItem(purchaseOrderBodyRecord.getItem());
					stockBalance.setWarehouse(purchaseOrder.getReceivingWarehouse());
					stockBalance.setUserName("admin");
					stockBalanceRepo.save(stockBalance);
				}
				else{
					stockBalance.setDateOfModification(new Date());
					stockBalance.setUserName("admin");
					Double previousQuantity = stockBalance.getExistingQuantity();
					stockBalance.setExistingQuantity(stockBalance.getExistingQuantity()+purchaseOrderBodyRecord.getReceivedQuantity());
					stockBalance.setForeignCurrencyAvertageValue((stockBalance.getForeignCurrencyAvertageValue()*previousQuantity+purchaseOrderBodyRecord.getForeignCurrencyTotalValue())/(previousQuantity+purchaseOrderBodyRecord.getReceivedQuantity()));
					stockBalance.setLocalCurrencyAvertageValue((stockBalance.getLocalCurrencyAvertageValue()*previousQuantity+purchaseOrderBodyRecord.getLocalCurrencyTotalValue())/(previousQuantity+purchaseOrderBodyRecord.getReceivedQuantity()));
					stockBalanceRepo.save(stockBalance);
				}
			}
			purchaseOrderRepo.save(purchaseOrder);
		}
		return itemReceiptInfo;
	}

	
}
