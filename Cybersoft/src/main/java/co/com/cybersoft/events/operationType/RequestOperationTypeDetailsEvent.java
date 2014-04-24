package co.com.cybersoft.events.operationType;

/**
 * Event class for OperationType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestOperationTypeDetailsEvent {

	private String id;
	
	private String description;
	
	public RequestOperationTypeDetailsEvent(String id){
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