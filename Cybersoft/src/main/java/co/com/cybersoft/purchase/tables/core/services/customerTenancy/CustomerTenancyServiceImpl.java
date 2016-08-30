package co.com.cybersoft.purchase.tables.core.services.customerTenancy;

import co.com.cybersoft.purchase.tables.events.customerTenancy.CreateCustomerTenancyEvent;
import co.com.cybersoft.purchase.tables.events.customerTenancy.CustomerTenancyDetailsEvent;
import co.com.cybersoft.purchase.tables.events.customerTenancy.CustomerTenancyPageEvent;
import co.com.cybersoft.purchase.tables.events.customerTenancy.CustomerTenancyModificationEvent;
import co.com.cybersoft.purchase.tables.events.customerTenancy.RequestCustomerTenancyDetailsEvent;
import co.com.cybersoft.purchase.tables.events.customerTenancy.RequestCustomerTenancyPageEvent;
import co.com.cybersoft.purchase.tables.persistence.services.customerTenancy.CustomerTenancyPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CustomerTenancyServiceImpl implements CustomerTenancyService{

	private final CustomerTenancyPersistenceService customerTenancyPersistenceService;
	
	public CustomerTenancyServiceImpl(final CustomerTenancyPersistenceService customerTenancyPersistenceService){
		this.customerTenancyPersistenceService=customerTenancyPersistenceService;
	}
	
	/**
	 */
	public CustomerTenancyDetailsEvent createCustomerTenancy(CreateCustomerTenancyEvent event ) throws Exception{
		return customerTenancyPersistenceService.createCustomerTenancy(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public CustomerTenancyPageEvent requestCustomerTenancyPage(RequestCustomerTenancyPageEvent event) throws Exception{
		return customerTenancyPersistenceService.requestCustomerTenancyPage(event);
	}

	public CustomerTenancyDetailsEvent requestCustomerTenancyDetails(RequestCustomerTenancyDetailsEvent event ) throws Exception{
		return customerTenancyPersistenceService.requestCustomerTenancyDetails(event);
	}

	public CustomerTenancyDetailsEvent modifyCustomerTenancy(CustomerTenancyModificationEvent event) throws Exception {
		return customerTenancyPersistenceService.modifyCustomerTenancy(event);
	}
	
	public CustomerTenancyDetailsEvent requestOnlyRecord() throws Exception {
		return customerTenancyPersistenceService.getOnlyRecord();
	}
	
	public CustomerTenancyPageEvent requestCustomerTenancyFilterPage(RequestCustomerTenancyPageEvent event) throws Exception {
		return customerTenancyPersistenceService.requestCustomerTenancyFilterPage(event);
	}
	
	public CustomerTenancyPageEvent requestCustomerTenancyFilter(RequestCustomerTenancyPageEvent event) throws Exception {
		return customerTenancyPersistenceService.requestCustomerTenancyFilter(event);
	}
	

	public CustomerTenancyPageEvent requestAllBySoftwareTradeMark(EmbeddedField... fields) throws Exception {
		return customerTenancyPersistenceService.requestAllBySoftwareTradeMark(fields);
	}public CustomerTenancyPageEvent requestAllBySoftwareVersion(EmbeddedField... fields) throws Exception {
		return customerTenancyPersistenceService.requestAllBySoftwareVersion(fields);
	}public CustomerTenancyPageEvent requestAllBySoftwareSerialNo(EmbeddedField... fields) throws Exception {
		return customerTenancyPersistenceService.requestAllBySoftwareSerialNo(fields);
	}public CustomerTenancyPageEvent requestAllByLocalCurrency(EmbeddedField... fields) throws Exception {
		return customerTenancyPersistenceService.requestAllByLocalCurrency(fields);
	}public CustomerTenancyPageEvent requestAllByForeignCurrency(EmbeddedField... fields) throws Exception {
		return customerTenancyPersistenceService.requestAllByForeignCurrency(fields);
	}
	
	
	
}