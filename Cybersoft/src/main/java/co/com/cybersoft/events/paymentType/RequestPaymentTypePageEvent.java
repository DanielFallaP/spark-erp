package co.com.cybersoft.events.paymentType;

import org.springframework.data.domain.Pageable;

/**
 * Event class for PaymentType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestPaymentTypePageEvent {

	private Pageable pageable;
	
	public RequestPaymentTypePageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}