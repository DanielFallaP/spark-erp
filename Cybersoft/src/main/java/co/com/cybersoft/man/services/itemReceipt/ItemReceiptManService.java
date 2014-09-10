package co.com.cybersoft.man.services.itemReceipt;

import co.com.cybersoft.docs.web.domain.itemReceipt.ItemReceiptInfo;

public interface ItemReceiptManService {
	ItemReceiptInfo processItemReceipt(ItemReceiptInfo itemReceiptInfo) throws Exception;
}
