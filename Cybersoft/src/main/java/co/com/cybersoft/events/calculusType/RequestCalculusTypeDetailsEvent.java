package co.com.cybersoft.events.calculusType;

/**
 * Event class for CalculusType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestCalculusTypeDetailsEvent {

	private String id;
	
	public RequestCalculusTypeDetailsEvent(String id){
		this.id=id;
	}

	public String getId() {
		return id;
	}
}