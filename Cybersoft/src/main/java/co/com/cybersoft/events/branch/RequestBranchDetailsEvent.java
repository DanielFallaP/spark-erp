package co.com.cybersoft.events.branch;

/**
 * Event class for Branch
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestBranchDetailsEvent {

	private String id;
	
	public RequestBranchDetailsEvent(String id){
		this.id=id;
	}

	public String getId() {
		return id;
	}
}