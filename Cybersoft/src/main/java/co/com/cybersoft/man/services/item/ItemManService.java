package co.com.cybersoft.man.services.item;

import co.com.cybersoft.docs.web.domain.requisition.RequisitionInfo;
import co.com.cybersoft.tables.web.domain.itemPurchaseHistory.ItemPurchaseHistoryInfo;


public interface ItemManService {
	ItemPurchaseHistoryInfo getItemLastPurchasePriceByDescription(String description) throws Exception;
	ItemPurchaseHistoryInfo getItemLastPurchasePriceByItemCode(String item) throws Exception;
	void checkAndSendVerificationMessage(String requestingUser, RequisitionInfo requisition) throws Exception;
}
