package co.com.cybersoft.purchase.tables.core.services.country;

import co.com.cybersoft.purchase.tables.events.country.CreateCountryEvent;
import co.com.cybersoft.purchase.tables.events.country.CountryDetailsEvent;
import co.com.cybersoft.purchase.tables.events.country.CountryPageEvent;
import co.com.cybersoft.purchase.tables.events.country.CountryModificationEvent;
import co.com.cybersoft.purchase.tables.events.country.RequestCountryDetailsEvent;
import co.com.cybersoft.purchase.tables.events.country.RequestCountryPageEvent;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface CountryService {
	CountryDetailsEvent requestOnlyRecord() throws Exception;

	CountryDetailsEvent createCountry(CreateCountryEvent event ) throws Exception;
	
	CountryPageEvent requestCountryPage(RequestCountryPageEvent event) throws Exception;

	CountryDetailsEvent requestCountryDetails(RequestCountryDetailsEvent event ) throws Exception;

	CountryDetailsEvent modifyCountry(CountryModificationEvent event) throws Exception;
	
	CountryPageEvent requestAllByRegion(EmbeddedField... fields) throws Exception;
	CountryPageEvent requestAllByCountry(EmbeddedField... fields) throws Exception;
	CountryPageEvent requestAllByLocalName(EmbeddedField... fields) throws Exception;
	CountryPageEvent requestAllByFrenchName(EmbeddedField... fields) throws Exception;

	
	CountryPageEvent requestByContainingCountry(String country) throws Exception;CountryPageEvent requestByContainingLocalName(String localName) throws Exception;CountryPageEvent requestByContainingFrenchName(String frenchName) throws Exception;
	
	CountryPageEvent requestCountryFilterPage(RequestCountryPageEvent event) throws Exception;

	CountryPageEvent requestCountryFilter(RequestCountryPageEvent event) throws Exception;
	
}