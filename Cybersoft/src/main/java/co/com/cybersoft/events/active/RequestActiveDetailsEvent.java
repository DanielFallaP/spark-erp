package co.com.cybersoft.events.active;

/**
 * Event class for Active
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestActiveDetailsEvent {

	private String id;
	
	public RequestActiveDetailsEvent(String id){
		this.id=id;
	}

	public String getId() {
		return id;
	}
}