package co.com.cybersoft.maintenance.tables.core.services.coin;

import co.com.cybersoft.maintenance.tables.events.coin.CreateCoinEvent;
import co.com.cybersoft.maintenance.tables.events.coin.CoinDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.coin.CoinPageEvent;
import co.com.cybersoft.maintenance.tables.events.coin.CoinModificationEvent;
import co.com.cybersoft.maintenance.tables.events.coin.RequestCoinDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.coin.RequestCoinPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.coin.CoinPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CoinServiceImpl implements CoinService{

	private final CoinPersistenceService coinPersistenceService;
	
	public CoinServiceImpl(final CoinPersistenceService coinPersistenceService){
		this.coinPersistenceService=coinPersistenceService;
	}
	
	/**
	 */
	public CoinDetailsEvent createCoin(CreateCoinEvent event ) throws Exception{
		return coinPersistenceService.createCoin(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public CoinPageEvent requestCoinPage(RequestCoinPageEvent event) throws Exception{
		return coinPersistenceService.requestCoinPage(event);
	}

	public CoinDetailsEvent requestCoinDetails(RequestCoinDetailsEvent event ) throws Exception{
		return coinPersistenceService.requestCoinDetails(event);
	}

	public CoinDetailsEvent modifyCoin(CoinModificationEvent event) throws Exception {
		return coinPersistenceService.modifyCoin(event);
	}
	
	public CoinDetailsEvent requestOnlyRecord() throws Exception {
		return coinPersistenceService.getOnlyRecord();
	}
	
	public CoinPageEvent requestCoinFilterPage(RequestCoinPageEvent event) throws Exception {
		return coinPersistenceService.requestCoinFilterPage(event);
	}
	
	public CoinPageEvent requestCoinFilter(RequestCoinPageEvent event) throws Exception {
		return coinPersistenceService.requestCoinFilter(event);
	}
	

	public CoinPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return coinPersistenceService.requestAllByCompany(fields);
	}public CoinPageEvent requestAllByNameCoin(EmbeddedField... fields) throws Exception {
		return coinPersistenceService.requestAllByNameCoin(fields);
	}public CoinPageEvent requestAllByAbbreviationCurrency(EmbeddedField... fields) throws Exception {
		return coinPersistenceService.requestAllByAbbreviationCurrency(fields);
	}
	
	
	
}