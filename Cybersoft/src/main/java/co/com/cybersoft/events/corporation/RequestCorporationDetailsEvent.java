package co.com.cybersoft.events.corporation;

/**
 * Event class for Corporation
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestCorporationDetailsEvent {

	private Integer id;
	
	private String description;
	
	public RequestCorporationDetailsEvent(Integer id){
		this.id=id;
	}

	public Integer getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}