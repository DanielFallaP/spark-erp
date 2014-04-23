package co.com.cybersoft.events.partner;

/**
 * Event class for Partner
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestPartnerDetailsEvent {

	private String id;
	
	public RequestPartnerDetailsEvent(String id){
		this.id=id;
	}

	public String getId() {
		return id;
	}
}