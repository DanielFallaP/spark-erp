package co.com.cybersoft.purchase.tables.persistence.services.exchangeRate;

import co.com.cybersoft.purchase.tables.persistence.repository.exchangeRate.ExchangeRateRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ExchangeRatePersistenceFactory {

	ExchangeRateRepository exchangeRateRepository;
	
	public ExchangeRatePersistenceFactory(ExchangeRateRepository exchangeRateRepository){
		this.exchangeRateRepository=exchangeRateRepository;
	}
	
	public co.com.cybersoft.purchase.tables.persistence.domain.ExchangeRate getExchangeRateByField(String field, String value){
		
		return null;
	}
}