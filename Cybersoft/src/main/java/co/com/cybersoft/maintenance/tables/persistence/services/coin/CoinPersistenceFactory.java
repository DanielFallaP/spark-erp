package co.com.cybersoft.maintenance.tables.persistence.services.coin;

import co.com.cybersoft.maintenance.tables.persistence.repository.coin.CoinRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CoinPersistenceFactory {

	CoinRepository coinRepository;
	
	public CoinPersistenceFactory(CoinRepository coinRepository){
		this.coinRepository=coinRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.Coin getCoinByField(String field, String value){
		if (field.equals("nameCoin"))
					return coinRepository.findByNameCoin(value);if (field.equals("abbreviationCurrency"))
					return coinRepository.findByAbbreviationCurrency(value);		
		return null;
	}
}