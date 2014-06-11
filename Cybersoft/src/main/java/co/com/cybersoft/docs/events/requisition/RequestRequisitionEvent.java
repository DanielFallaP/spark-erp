package co.com.cybersoft.docs.events.requisition;

/**
 * Event class for Requisition
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestRequisitionEvent {

	private String id;
	
	private String field;
	
	private String value;
	
	public RequestRequisitionEvent(String id){
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
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}	
}