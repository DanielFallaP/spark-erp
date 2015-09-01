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
	@Override
	public ExchangeRateDetailsEvent createExchangeRate(CreateExchangeRateEvent event ) throws Exception{
		return exchangeRatePersistenceService.createExchangeRate(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	@Override
	public ExchangeRatePageEvent requestExchangeRatePage(RequestExchangeRatePageEvent event) throws Exception{
		return exchangeRatePersistenceService.requestExchangeRatePage(event);
	}

	@Override
	public ExchangeRateDetailsEvent requestExchangeRateDetails(RequestExchangeRateDetailsEvent event ) throws Exception{
		return exchangeRatePersistenceService.requestExchangeRateDetails(event);
	}

	@Override
	public ExchangeRateDetailsEvent modifyExchangeRate(ExchangeRateModificationEvent event) throws Exception {
		return exchangeRatePersistenceService.modifyExchangeRate(event);
	}
	
	@Override
	public ExchangeRateDetailsEvent requestOnlyRecord() throws Exception {
		return exchangeRatePersistenceService.getOnlyRecord();
	}
	
	@Override
	public ExchangeRatePageEvent requestExchangeRateFilterPage(RequestExchangeRatePageEvent event) throws Exception {
		return exchangeRatePersistenceService.requestExchangeRateFilterPage(event);
	}

	@Override
	public ExchangeRatePageEvent requestAllByLocalCurrency(EmbeddedField... fields) throws Exception {
		return exchangeRatePersistenceService.requestAllByLocalCurrency(fields);
	}@Override
	public ExchangeRatePageEvent requestAllByCodeName(String code)
		throws Exception {
		return exchangeRatePersistenceService.requestAllByCodeName(code);
	}@Override
	public ExchangeRatePageEvent requestAllByForeignCurrency(EmbeddedField... fields) throws Exception {
		return exchangeRatePersistenceService.requestAllByForeignCurrency(fields);
	}

	@Override
	public ExchangeRatePageEvent requestExchangeRateFilter(RequestExchangeRatePageEvent event) throws Exception {
		return exchangeRatePersistenceService.requestExchangeRateFilter(event);
	}
	
	
	
}