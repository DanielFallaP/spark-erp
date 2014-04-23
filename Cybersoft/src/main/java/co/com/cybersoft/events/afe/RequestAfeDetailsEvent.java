package co.com.cybersoft.events.afe;

/**
 * Event class for Afe
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestAfeDetailsEvent {

	private String id;
	
	public RequestAfeDetailsEvent(String id){
		this.id=id;
	}

	public String getId() {
		return id;
	}
}