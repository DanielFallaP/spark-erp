package co.com.cybersoft.events.branch;

/**
 * Event class for Branch
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestBranchDetailsEvent {

	private Integer id;
	
	private String description;
	
	public RequestBranchDetailsEvent(Integer id){
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