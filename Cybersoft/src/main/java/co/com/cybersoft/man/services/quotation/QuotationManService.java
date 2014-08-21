package co.com.cybersoft.man.services.quotation;

import co.com.cybersoft.docs.web.domain.quotation.QuotationBodyInfo;
import co.com.cybersoft.docs.web.domain.quotation.QuotationInfo;

public interface QuotationManService {
	
	void checkQuotationBody(QuotationBodyInfo quotationInfo) throws Exception;
	
	void generatePurchaseOrders(QuotationInfo quotationInfo) throws Exception;

}
