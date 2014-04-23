package co.com.cybersoft.events.afeType;

/**
 * Event class for AfeType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestAfeTypeDetailsEvent {

	private String id;
	
	public RequestAfeTypeDetailsEvent(String id){
		this.id=id;
	}

	public String getId() {
		return id;
	}
}