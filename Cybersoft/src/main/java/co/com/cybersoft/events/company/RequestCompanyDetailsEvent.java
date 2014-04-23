package co.com.cybersoft.events.company;

/**
 * Event class for Company
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestCompanyDetailsEvent {

	private String id;
	
	public RequestCompanyDetailsEvent(String id){
		this.id=id;
	}

	public String getId() {
		return id;
	}
}