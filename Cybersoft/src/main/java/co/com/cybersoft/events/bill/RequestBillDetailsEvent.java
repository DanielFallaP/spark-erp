package co.com.cybersoft.events.bill;

/**
 * Event class for Bill
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestBillDetailsEvent {

	private String id;
	
	public RequestBillDetailsEvent(String id){
		this.id=id;
	}

	public String getId() {
		return id;
	}
}