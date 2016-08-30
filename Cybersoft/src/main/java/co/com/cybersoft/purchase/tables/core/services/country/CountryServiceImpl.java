package co.com.cybersoft.purchase.tables.core.services.country;

import co.com.cybersoft.purchase.tables.events.country.CreateCountryEvent;
import co.com.cybersoft.purchase.tables.events.country.CountryDetailsEvent;
import co.com.cybersoft.purchase.tables.events.country.CountryPageEvent;
import co.com.cybersoft.purchase.tables.events.country.CountryModificationEvent;
import co.com.cybersoft.purchase.tables.events.country.RequestCountryDetailsEvent;
import co.com.cybersoft.purchase.tables.events.country.RequestCountryPageEvent;
import co.com.cybersoft.purchase.tables.persistence.services.country.CountryPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CountryServiceImpl implements CountryService{

	private final CountryPersistenceService countryPersistenceService;
	
	public CountryServiceImpl(final CountryPersistenceService countryPersistenceService){
		this.countryPersistenceService=countryPersistenceService;
	}
	
	/**
	 */
	public CountryDetailsEvent createCountry(CreateCountryEvent event ) throws Exception{
		return countryPersistenceService.createCountry(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public CountryPageEvent requestCountryPage(RequestCountryPageEvent event) throws Exception{
		return countryPersistenceService.requestCountryPage(event);
	}

	public CountryDetailsEvent requestCountryDetails(RequestCountryDetailsEvent event ) throws Exception{
		return countryPersistenceService.requestCountryDetails(event);
	}

	public CountryDetailsEvent modifyCountry(CountryModificationEvent event) throws Exception {
		return countryPersistenceService.modifyCountry(event);
	}
	
	public CountryDetailsEvent requestOnlyRecord() throws Exception {
		return countryPersistenceService.getOnlyRecord();
	}
	
	public CountryPageEvent requestCountryFilterPage(RequestCountryPageEvent event) throws Exception {
		return countryPersistenceService.requestCountryFilterPage(event);
	}
	
	public CountryPageEvent requestCountryFilter(RequestCountryPageEvent event) throws Exception {
		return countryPersistenceService.requestCountryFilter(event);
	}
	

	public CountryPageEvent requestAllByRegion(EmbeddedField... fields) throws Exception {
		return countryPersistenceService.requestAllByRegion(fields);
	}public CountryPageEvent requestAllByCountry(EmbeddedField... fields) throws Exception {
		return countryPersistenceService.requestAllByCountry(fields);
	}public CountryPageEvent requestAllByLocalName(EmbeddedField... fields) throws Exception {
		return countryPersistenceService.requestAllByLocalName(fields);
	}public CountryPageEvent requestAllByFrenchName(EmbeddedField... fields) throws Exception {
		return countryPersistenceService.requestAllByFrenchName(fields);
	}
	
	public CountryPageEvent requestByContainingCountry(String country) throws Exception {
				return countryPersistenceService.requestByContainingCountry(country);
			}public CountryPageEvent requestByContainingLocalName(String localName) throws Exception {
				return countryPersistenceService.requestByContainingLocalName(localName);
			}public CountryPageEvent requestByContainingFrenchName(String frenchName) throws Exception {
				return countryPersistenceService.requestByContainingFrenchName(frenchName);
			}
	
	
}