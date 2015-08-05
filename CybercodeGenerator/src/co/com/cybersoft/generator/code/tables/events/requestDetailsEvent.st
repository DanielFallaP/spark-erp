package co.com.cybersoft.$module$.tables.events.$tableName$;

/**
 * Event class for $entityName$
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class Request$entityName$DetailsEvent {

	private Long id;
	
	private String field;
	
	private String value;
	
	public Request$entityName$DetailsEvent(Long id){
		this.id=id;
	}

	public Long getId() {
		return id;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}	
}