package co.com.cybersoft.events.$tableName$;

/**
 * Event class for $entityName$
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class Request$entityName$DetailsEvent {

	private String id;
	
	private String field;
	
	public Request$entityName$DetailsEvent(String id){
		this.id=id;
	}

	public String getId() {
		return id;
	}
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
}