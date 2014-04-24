package co.com.cybersoft.events.company;

/**
 * Event class for Company
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestCompanyDetailsEvent {

	private Integer id;
	
	private String description;
	
	public RequestCompanyDetailsEvent(Integer id){
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