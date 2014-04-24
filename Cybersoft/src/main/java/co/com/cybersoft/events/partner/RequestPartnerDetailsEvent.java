package co.com.cybersoft.events.partner;

/**
 * Event class for Partner
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestPartnerDetailsEvent {

	private Integer id;
	
	private String description;
	
	public RequestPartnerDetailsEvent(Integer id){
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