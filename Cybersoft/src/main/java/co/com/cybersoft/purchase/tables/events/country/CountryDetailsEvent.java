package co.com.cybersoft.purchase.tables.events.country;

import co.com.cybersoft.purchase.tables.core.domain.CountryDetails;

/**
 * Event class for Country
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CountryDetailsEvent {
	
	private CountryDetails countryDetails;
	
	public CountryDetailsEvent(CountryDetails countryDetails){
		this.countryDetails=countryDetails;
	}

	public CountryDetails getCountryDetails() {
		return countryDetails;
	}

}