package co.com.cybersoft.events.paymentType;

import org.springframework.data.domain.Page;

import co.com.cybersoft.persistence.domain.PaymentType;

/**
 * Event class for PaymentType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class PaymentTypePageEvent {
	private Page<PaymentType> paymentTypePage;
	
	public PaymentTypePageEvent(Page<PaymentType>  paymentTypePage){
		this.paymentTypePage= paymentTypePage;
	}

	public Page<PaymentType> getPaymentTypePage() {
		return paymentTypePage;
	}
	
}