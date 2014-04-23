package co.com.cybersoft.events.contract;

/**
 * Event class for Contract
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestContractDetailsEvent {

	private String id;
	
	public RequestContractDetailsEvent(String id){
		this.id=id;
	}

	public String getId() {
		return id;
	}
}