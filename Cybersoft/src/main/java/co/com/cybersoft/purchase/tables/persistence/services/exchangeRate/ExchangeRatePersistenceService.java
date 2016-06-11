package co.com.cybersoft.purchase.tables.persistence.services.exchangeRate;

import co.com.cybersoft.purchase.tables.events.exchangeRate.CreateExchangeRateEvent;
import co.com.cybersoft.purchase.tables.events.exchangeRate.ExchangeRateDetailsEvent;
import co.com.cybersoft.purchase.tables.events.exchangeRate.ExchangeRatePageEvent;
import co.com.cybersoft.purchase.tables.events.exchangeRate.ExchangeRateModificationEvent;
import co.com.cybersoft.purchase.tables.events.exchangeRate.RequestExchangeRateDetailsEvent;
import co.com.cybersoft.purchase.tables.events.exchangeRate.RequestExchangeRatePageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface ExchangeRatePersistenceService {

	ExchangeRateDetailsEvent createExchangeRate(CreateExchangeRateEvent event) throws Exception;

	ExchangeRatePageEvent requestExchangeRatePage(RequestExchangeRatePageEvent event) throws Exception;

	ExchangeRateDetailsEvent requestExchangeRateDetails(RequestExchangeRateDetailsEvent event) throws Exception;
	
	ExchangeRateDetailsEvent modifyExchangeRate(ExchangeRateModificationEvent event) throws Exception;
	ExchangeRatePageEvent requestExchangeRateFilterPage(RequestExchangeRatePageEvent event) throws Exception;
	ExchangeRatePageEvent requestExchangeRateFilter(RequestExchangeRatePageEvent event) throws Exception;
	
	ExchangeRatePageEvent requestAllByLocalCurrency(EmbeddedField... fields) throws Exception;
	ExchangeRatePageEvent requestAllByCodeName(String code) throws Exception;
	ExchangeRatePageEvent requestAllByForeignCurrency(EmbeddedField... fields) throws Exception;

	
	
	ExchangeRateDetailsEvent getOnlyRecord() throws Exception;
	
}