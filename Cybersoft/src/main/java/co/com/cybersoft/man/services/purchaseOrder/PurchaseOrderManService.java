package co.com.cybersoft.man.services.purchaseOrder;

import co.com.cybersoft.purchase.docs.web.domain.purchaseOrder.PurchaseOrderInfo;

public interface PurchaseOrderManService {
	void processPurchaseOrder(String requestingUser, PurchaseOrderInfo purchaseOrderInfo) throws Exception;
}
