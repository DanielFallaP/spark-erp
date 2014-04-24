package co.com.cybersoft.core.services.bill;

import co.com.cybersoft.events.bill.CreateBillEvent;
import co.com.cybersoft.events.bill.BillDetailsEvent;
import co.com.cybersoft.events.bill.BillPageEvent;
import co.com.cybersoft.events.bill.BillModificationEvent;
import co.com.cybersoft.events.bill.RequestBillDetailsEvent;
import co.com.cybersoft.events.bill.RequestBillPageEvent;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface BillService {
	BillDetailsEvent createBill(CreateBillEvent event ) throws Exception;
	
	BillPageEvent requestBillPage(RequestBillPageEvent event) throws Exception;

	BillDetailsEvent requestBillDetails(RequestBillDetailsEvent event ) throws Exception;

	BillDetailsEvent modifyBill(BillModificationEvent event) throws Exception;
	
	BillPageEvent requestAll() throws Exception;
	
	BillPageEvent requestByCodePrefix(String code) throws Exception;
	
	BillPageEvent requestByContainingDescription(String description) throws Exception;
	
}