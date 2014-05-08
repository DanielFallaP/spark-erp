package co.com.cybersoft.events.$tableName$;

/**
 * Event class for $entityName$
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class Request$entityName$DetailsEvent {

	private String id;
	
	private String description;
	
	public Request$entityName$DetailsEvent(String id){
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