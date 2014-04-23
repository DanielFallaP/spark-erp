package co.com.cybersoft.events.bill;

import co.com.cybersoft.core.domain.BillDetails;

/**
 * Event class for Bill
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class BillModificationEvent {

	private BillDetails billDetails;
	
	public BillModificationEvent(BillDetails billDetails){
		this.billDetails=billDetails;
	}

	public BillDetails getBillDetails() {
		return billDetails;
	}
	
}