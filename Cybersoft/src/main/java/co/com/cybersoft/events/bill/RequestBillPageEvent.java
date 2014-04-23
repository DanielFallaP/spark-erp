package co.com.cybersoft.events.bill;

import org.springframework.data.domain.Pageable;

/**
 * Event class for Bill
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestBillPageEvent {

	private Pageable pageable;
	
	public RequestBillPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}