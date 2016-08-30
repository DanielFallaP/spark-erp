package co.com.cybersoft.purchase.tables.persistence.services.country;

import co.com.cybersoft.purchase.tables.persistence.repository.country.CountryRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CountryPersistenceFactory {

	CountryRepository countryRepository;
	
	public CountryPersistenceFactory(CountryRepository countryRepository){
		this.countryRepository=countryRepository;
	}
	
	public co.com.cybersoft.purchase.tables.persistence.domain.Country getCountryByField(String field, String value){
		if (field.equals("country"))
					return countryRepository.findByCountry(value);if (field.equals("localName"))
					return countryRepository.findByLocalName(value);if (field.equals("frenchName"))
					return countryRepository.findByFrenchName(value);		
		return null;
	}
}