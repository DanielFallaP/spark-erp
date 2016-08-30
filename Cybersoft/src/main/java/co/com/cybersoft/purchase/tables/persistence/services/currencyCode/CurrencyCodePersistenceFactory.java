package co.com.cybersoft.purchase.tables.persistence.services.currencyCode;

import co.com.cybersoft.purchase.tables.persistence.repository.currencyCode.CurrencyCodeRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CurrencyCodePersistenceFactory {

	CurrencyCodeRepository currencyCodeRepository;
	
	public CurrencyCodePersistenceFactory(CurrencyCodeRepository currencyCodeRepository){
		this.currencyCodeRepository=currencyCodeRepository;
	}
	
	public co.com.cybersoft.purchase.tables.persistence.domain.CurrencyCode getCurrencyCodeByField(String field, String value){
		if (field.equals("currencyName"))
					return currencyCodeRepository.findByCurrencyName(value);if (field.equals("currency"))
					return currencyCodeRepository.findByCurrency(value);if (field.equals("symbol"))
					return currencyCodeRepository.findBySymbol(value);if (field.equals("hex1"))
					return currencyCodeRepository.findByHex1(value);if (field.equals("hex2"))
					return currencyCodeRepository.findByHex2(value);if (field.equals("hex3"))
					return currencyCodeRepository.findByHex3(value);		
		return null;
	}
}