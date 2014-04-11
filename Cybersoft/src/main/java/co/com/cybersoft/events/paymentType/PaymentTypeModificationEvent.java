package co.com.cybersoft.events.paymentType;

import co.com.cybersoft.core.domain.PaymentTypeDetails;

/**
 * Event class for PaymentType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class PaymentTypeModificationEvent {

	private PaymentTypeDetails paymentTypeDetails;
	
	public PaymentTypeModificationEvent(PaymentTypeDetails paymentTypeDetails){
		this.paymentTypeDetails=paymentTypeDetails;
	}

	public PaymentTypeDetails getPaymentTypeDetails() {
		return paymentTypeDetails;
	}
	
}