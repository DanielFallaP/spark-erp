package co.com.cybersoft.events.paymentType;

/**
 * Event class for PaymentType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestPaymentTypeDetailsEvent {

	private String id;
	
	public RequestPaymentTypeDetailsEvent(String id){
		this.id=id;
	}

	public String getId() {
		return id;
	}
}