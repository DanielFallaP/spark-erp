package co.com.cybersoft.maintenance.tables.events.physicalLocation;

/**
 * Event class for PhysicalLocation
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestPhysicalLocationDetailsEvent {

	private Long id;
	
	private String field;
	
	private String value;
	
	public RequestPhysicalLocationDetailsEvent(Long id){
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