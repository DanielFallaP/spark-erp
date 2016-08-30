package co.com.cybersoft.purchase.tables.persistence.services.customerTenancy;

import co.com.cybersoft.purchase.tables.persistence.repository.customerTenancy.CustomerTenancyRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CustomerTenancyPersistenceFactory {

	CustomerTenancyRepository customerTenancyRepository;
	
	public CustomerTenancyPersistenceFactory(CustomerTenancyRepository customerTenancyRepository){
		this.customerTenancyRepository=customerTenancyRepository;
	}
	
	public co.com.cybersoft.purchase.tables.persistence.domain.CustomerTenancy getCustomerTenancyByField(String field, String value){
		if (field.equals("softwareTradeMark"))
					return customerTenancyRepository.findBySoftwareTradeMark(value);if (field.equals("softwareVersion"))
					return customerTenancyRepository.findBySoftwareVersion(value);if (field.equals("softwareSerialNo"))
					return customerTenancyRepository.findBySoftwareSerialNo(value);		
		return null;
	}
}