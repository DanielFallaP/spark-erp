package co.com.cybersoft.purchase.tables.core.services.exchangeRate;

import co.com.cybersoft.purchase.tables.events.exchangeRate.CreateExchangeRateEvent;
import co.com.cybersoft.purchase.tables.events.exchangeRate.ExchangeRateDetailsEvent;
import co.com.cybersoft.purchase.tables.events.exchangeRate.ExchangeRatePageEvent;
import co.com.cybersoft.purchase.tables.events.exchangeRate.ExchangeRateModificationEvent;
import co.com.cybersoft.purchase.tables.events.exchangeRate.RequestExchangeRateDetailsEvent;
import co.com.cybersoft.purchase.tables.events.exchangeRate.RequestExchangeRatePageEvent;
import co.com.cybersoft.purchase.tables.persistence.services.exchangeRate.ExchangeRatePersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ExchangeRateServiceImpl implements ExchangeRateService{

	private final ExchangeRatePersistenceService exchangeRatePersistenceService;
	
	public ExchangeRateServiceImpl(final ExchangeRatePersistenceService exchangeRatePersistenceService){
		this.exchangeRatePersistenceService=exchangeRatePersistenceService;
	}
	
	/**
	 */
	public ExchangeRateDetailsEvent createExchangeRate(CreateExchangeRateEvent event ) throws Exception{
		return exchangeRatePersistenceService.createExchangeRate(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public ExchangeRatePageEvent requestExchangeRatePage(RequestExchangeRatePageEvent event) throws Exception{
		return exchangeRatePersistenceService.requestExchangeRatePage(event);
	}

	public ExchangeRateDetailsEvent requestExchangeRateDetails(RequestExchangeRateDetailsEvent event ) throws Exception{
		return exchangeRatePersistenceService.requestExchangeRateDetails(event);
	}

	public ExchangeRateDetailsEvent modifyExchangeRate(ExchangeRateModificationEvent event) throws Exception {
		return exchangeRatePersistenceService.modifyExchangeRate(event);
	}
	
	public ExchangeRateDetailsEvent requestOnlyRecord() throws Exception {
		return exchangeRatePersistenceService.getOnlyRecord();
	}
	
	public ExchangeRatePageEvent requestExchangeRateFilterPage(RequestExchangeRatePageEvent event) throws Exception {
		return exchangeRatePersistenceService.requestExchangeRateFilterPage(event);
	}
	
	public ExchangeRatePageEvent requestExchangeRateFilter(RequestExchangeRatePageEvent event) throws Exception {
		return exchangeRatePersistenceService.requestExchangeRateFilter(event);
	}
	

	public ExchangeRatePageEvent requestAllByLocalCurrency(EmbeddedField... fields) throws Exception {
		return exchangeRatePersistenceService.requestAllByLocalCurrency(fields);
	}public ExchangeRatePageEvent requestAllByCodeName(String code)
		throws Exception {
		return exchangeRatePersistenceService.requestAllByCodeName(code);
	}public ExchangeRatePageEvent requestAllByForeignCurrency(EmbeddedField... fields) throws Exception {
		return exchangeRatePersistenceService.requestAllByForeignCurrency(fields);
	}
	
	
	
}