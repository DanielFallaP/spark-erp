package co.com.cybersoft.events.wareHouse;

/**
 * Event class for WareHouse
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestWareHouseDetailsEvent {

	private String id;
	
	private String description;
	
	public RequestWareHouseDetailsEvent(String id){
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