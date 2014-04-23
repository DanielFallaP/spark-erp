package co.com.cybersoft.events.corporation;

/**
 * Event class for Corporation
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestCorporationDetailsEvent {

	private String id;
	
	public RequestCorporationDetailsEvent(String id){
		this.id=id;
	}

	public String getId() {
		return id;
	}
}