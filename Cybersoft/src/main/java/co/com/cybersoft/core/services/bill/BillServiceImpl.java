package co.com.cybersoft.core.services.bill;

import co.com.cybersoft.events.bill.CreateBillEvent;
import co.com.cybersoft.events.bill.BillDetailsEvent;
import co.com.cybersoft.events.bill.BillPageEvent;
import co.com.cybersoft.events.bill.BillModificationEvent;
import co.com.cybersoft.events.bill.RequestBillDetailsEvent;
import co.com.cybersoft.events.bill.RequestBillPageEvent;
import co.com.cybersoft.persistence.services.bill.BillPersistenceService;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class BillServiceImpl implements BillService{

	private final BillPersistenceService billPersistenceService;
	
	public BillServiceImpl(final BillPersistenceService billPersistenceService){
		this.billPersistenceService=billPersistenceService;
	}
	
	/**
	 */
	@Override
	public BillDetailsEvent createBill(CreateBillEvent event ) throws Exception{
		return billPersistenceService.createBill(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	@Override
	public BillPageEvent requestBillPage(RequestBillPageEvent event) throws Exception{
		return billPersistenceService.requestBillPage(event);
	}

	@Override
	public BillDetailsEvent requestBillDetails(RequestBillDetailsEvent event ) throws Exception{
		return billPersistenceService.requestBillDetails(event);
	}

	@Override
	public BillDetailsEvent modifyBill(BillModificationEvent event) throws Exception {
		return billPersistenceService.modifyBill(event);
	}
	
	@Override
	public BillPageEvent requestAll() throws Exception {
		return billPersistenceService.requestAll();
	}
	
	@Override
	public BillPageEvent requestByCodePrefix(String codePrefix) throws Exception {
		return billPersistenceService.requestByCodePrefix(codePrefix);
	}

	@Override
	public BillPageEvent requestByContainingDescription(String description) throws Exception {
		return billPersistenceService.requestByContainingDescription(description);
	}
	
}