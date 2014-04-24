package co.com.cybersoft.events.jointVenture;

/**
 * Event class for JointVenture
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestJointVentureDetailsEvent {

	private String id;
	
	private String description;
	
	public RequestJointVentureDetailsEvent(String id){
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