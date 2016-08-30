package co.com.cybersoft.purchase.tables.persistence.services.currency;

import co.com.cybersoft.purchase.tables.persistence.repository.currency.CurrencyRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CurrencyPersistenceFactory {

	CurrencyRepository currencyRepository;
	
	public CurrencyPersistenceFactory(CurrencyRepository currencyRepository){
		this.currencyRepository=currencyRepository;
	}
	
	public co.com.cybersoft.purchase.tables.persistence.domain.Currency getCurrencyByField(String field, String value){
		if (field.equals("currency"))
					return currencyRepository.findByCurrency(value);		
		return null;
	}
}