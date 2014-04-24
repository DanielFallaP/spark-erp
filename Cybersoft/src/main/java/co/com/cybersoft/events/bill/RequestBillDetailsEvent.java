package co.com.cybersoft.events.bill;

/**
 * Event class for Bill
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestBillDetailsEvent {

	private String id;
	
	private String description;
	
	public RequestBillDetailsEvent(String id){
		this.id=id;
	}

	public String getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}