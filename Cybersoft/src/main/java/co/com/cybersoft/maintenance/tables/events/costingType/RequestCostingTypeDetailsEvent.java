package co.com.cybersoft.maintenance.tables.events.costingType;

/**
 * Event class for CostingType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestCostingTypeDetailsEvent {

	private Long id;
	
	private String field;
	
	private String value;
	
	public RequestCostingTypeDetailsEvent(Long id){
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