package co.com.cybersoft.events.calculusType;

/**
 * Event class for CalculusType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestCalculusTypeDetailsEvent {

	private String id;
	
	private String description;
	
	public RequestCalculusTypeDetailsEvent(String id){
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