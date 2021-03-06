package co.com.cybersoft.purchase.tables.events.customerTenancy;

import co.com.cybersoft.purchase.tables.core.domain.CustomerTenancyDetails;

/**
 * Event class for CustomerTenancy
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CustomerTenancyDetailsEvent {
	
	private CustomerTenancyDetails customerTenancyDetails;
	
	public CustomerTenancyDetailsEvent(CustomerTenancyDetails customerTenancyDetails){
		this.customerTenancyDetails=customerTenancyDetails;
	}

	public CustomerTenancyDetails getCustomerTenancyDetails() {
		return customerTenancyDetails;
	}

}