package co.com.cybersoft.events.items;

/**
 * Event class for Items
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestItemsDetailsEvent {

	private String id;
	
	private String description;
	
	public RequestItemsDetailsEvent(String id){
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