package co.com.cybersoft.events.afe;

/**
 * Event class for Afe
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestAfeDetailsEvent {

	private String id;
	
	private String description;
	
	public RequestAfeDetailsEvent(String id){
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