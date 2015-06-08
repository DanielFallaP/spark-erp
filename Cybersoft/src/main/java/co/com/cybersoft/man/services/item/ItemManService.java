package co.com.cybersoft.man.services.item;

import co.com.cybersoft.purchase.tables.web.domain.itemPurchaseHistory.ItemPurchaseHistoryInfo;


public interface ItemManService {
	ItemPurchaseHistoryInfo getItemLastPurchasePriceByDescription(String description) throws Exception;
	ItemPurchaseHistoryInfo getItemLastPurchasePriceByItemCode(String item) throws Exception;
}
