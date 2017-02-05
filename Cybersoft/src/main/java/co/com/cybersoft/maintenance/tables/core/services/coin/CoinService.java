package co.com.cybersoft.maintenance.tables.core.services.coin;

import co.com.cybersoft.maintenance.tables.events.coin.CreateCoinEvent;
import co.com.cybersoft.maintenance.tables.events.coin.CoinDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.coin.CoinPageEvent;
import co.com.cybersoft.maintenance.tables.events.coin.CoinModificationEvent;
import co.com.cybersoft.maintenance.tables.events.coin.RequestCoinDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.coin.RequestCoinPageEvent;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface CoinService {
	CoinDetailsEvent requestOnlyRecord() throws Exception;

	CoinDetailsEvent createCoin(CreateCoinEvent event ) throws Exception;
	
	CoinPageEvent requestCoinPage(RequestCoinPageEvent event) throws Exception;

	CoinDetailsEvent requestCoinDetails(RequestCoinDetailsEvent event ) throws Exception;

	CoinDetailsEvent modifyCoin(CoinModificationEvent event) throws Exception;
	
	CoinPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	CoinPageEvent requestAllByNameCoin(EmbeddedField... fields) throws Exception;
	CoinPageEvent requestAllByAbbreviationCurrency(EmbeddedField... fields) throws Exception;

	
	
	CoinPageEvent requestCoinFilterPage(RequestCoinPageEvent event) throws Exception;

	CoinPageEvent requestCoinFilter(RequestCoinPageEvent event) throws Exception;
	
}