package co.com.cybersoft.maintenance.tables.events.operation;

/**
 * Event class for Operation
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestOperationDetailsEvent {

	private Long id;
	
	private String field;
	
	private String value;
	
	public RequestOperationDetailsEvent(Long id){
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