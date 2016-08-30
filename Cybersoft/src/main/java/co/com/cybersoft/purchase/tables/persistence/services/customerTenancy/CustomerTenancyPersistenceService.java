package co.com.cybersoft.purchase.tables.persistence.services.customerTenancy;

import co.com.cybersoft.purchase.tables.events.customerTenancy.CreateCustomerTenancyEvent;
import co.com.cybersoft.purchase.tables.events.customerTenancy.CustomerTenancyDetailsEvent;
import co.com.cybersoft.purchase.tables.events.customerTenancy.CustomerTenancyPageEvent;
import co.com.cybersoft.purchase.tables.events.customerTenancy.CustomerTenancyModificationEvent;
import co.com.cybersoft.purchase.tables.events.customerTenancy.RequestCustomerTenancyDetailsEvent;
import co.com.cybersoft.purchase.tables.events.customerTenancy.RequestCustomerTenancyPageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface CustomerTenancyPersistenceService {

	CustomerTenancyDetailsEvent createCustomerTenancy(CreateCustomerTenancyEvent event) throws Exception;

	CustomerTenancyPageEvent requestCustomerTenancyPage(RequestCustomerTenancyPageEvent event) throws Exception;

	CustomerTenancyDetailsEvent requestCustomerTenancyDetails(RequestCustomerTenancyDetailsEvent event) throws Exception;
	
	CustomerTenancyDetailsEvent modifyCustomerTenancy(CustomerTenancyModificationEvent event) throws Exception;
	CustomerTenancyPageEvent requestCustomerTenancyFilterPage(RequestCustomerTenancyPageEvent event) throws Exception;
	CustomerTenancyPageEvent requestCustomerTenancyFilter(RequestCustomerTenancyPageEvent event) throws Exception;
	
	CustomerTenancyPageEvent requestAllBySoftwareTradeMark(EmbeddedField... fields) throws Exception;
	CustomerTenancyPageEvent requestAllBySoftwareVersion(EmbeddedField... fields) throws Exception;
	CustomerTenancyPageEvent requestAllBySoftwareSerialNo(EmbeddedField... fields) throws Exception;
	CustomerTenancyPageEvent requestAllByLocalCurrency(EmbeddedField... fields) throws Exception;
	CustomerTenancyPageEvent requestAllByForeignCurrency(EmbeddedField... fields) throws Exception;

	
	
	CustomerTenancyDetailsEvent getOnlyRecord() throws Exception;
	
}