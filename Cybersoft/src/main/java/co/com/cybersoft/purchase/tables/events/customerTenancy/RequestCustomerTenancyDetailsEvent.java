package co.com.cybersoft.purchase.tables.events.customerTenancy;

/**
 * Event class for CustomerTenancy
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestCustomerTenancyDetailsEvent {

	private Long id;
	
	private String field;
	
	private String value;
	
	public RequestCustomerTenancyDetailsEvent(Long id){
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