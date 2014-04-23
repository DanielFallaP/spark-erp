package co.com.cybersoft.events.bill;

import co.com.cybersoft.core.domain.BillDetails;

/**
 * Event class for Bill
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class BillDetailsEvent {
	
	private BillDetails billDetails;
	
	public BillDetailsEvent(BillDetails billDetails){
		this.billDetails=billDetails;
	}

	public BillDetails getBillDetails() {
		return billDetails;
	}

}