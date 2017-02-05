package co.com.cybersoft.maintenance.tables.events.responsible;

/**
 * Event class for Responsible
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestResponsibleDetailsEvent {

	private Long id;
	
	private String field;
	
	private String value;
	
	public RequestResponsibleDetailsEvent(Long id){
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