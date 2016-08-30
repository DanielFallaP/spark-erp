package co.com.cybersoft.purchase.tables.core.services.currency;

import co.com.cybersoft.purchase.tables.events.currency.CreateCurrencyEvent;
import co.com.cybersoft.purchase.tables.events.currency.CurrencyDetailsEvent;
import co.com.cybersoft.purchase.tables.events.currency.CurrencyPageEvent;
import co.com.cybersoft.purchase.tables.events.currency.CurrencyModificationEvent;
import co.com.cybersoft.purchase.tables.events.currency.RequestCurrencyDetailsEvent;
import co.com.cybersoft.purchase.tables.events.currency.RequestCurrencyPageEvent;
import co.com.cybersoft.purchase.tables.persistence.services.currency.CurrencyPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CurrencyServiceImpl implements CurrencyService{

	private final CurrencyPersistenceService currencyPersistenceService;
	
	public CurrencyServiceImpl(final CurrencyPersistenceService currencyPersistenceService){
		this.currencyPersistenceService=currencyPersistenceService;
	}
	
	/**
	 */
	public CurrencyDetailsEvent createCurrency(CreateCurrencyEvent event ) throws Exception{
		return currencyPersistenceService.createCurrency(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public CurrencyPageEvent requestCurrencyPage(RequestCurrencyPageEvent event) throws Exception{
		return currencyPersistenceService.requestCurrencyPage(event);
	}

	public CurrencyDetailsEvent requestCurrencyDetails(RequestCurrencyDetailsEvent event ) throws Exception{
		return currencyPersistenceService.requestCurrencyDetails(event);
	}

	public CurrencyDetailsEvent modifyCurrency(CurrencyModificationEvent event) throws Exception {
		return currencyPersistenceService.modifyCurrency(event);
	}
	
	public CurrencyDetailsEvent requestOnlyRecord() throws Exception {
		return currencyPersistenceService.getOnlyRecord();
	}
	
	public CurrencyPageEvent requestCurrencyFilterPage(RequestCurrencyPageEvent event) throws Exception {
		return currencyPersistenceService.requestCurrencyFilterPage(event);
	}
	
	public CurrencyPageEvent requestCurrencyFilter(RequestCurrencyPageEvent event) throws Exception {
		return currencyPersistenceService.requestCurrencyFilter(event);
	}
	

	public CurrencyPageEvent requestAllByCode(EmbeddedField... fields) throws Exception {
		return currencyPersistenceService.requestAllByCode(fields);
	}public CurrencyPageEvent requestAllByCurrency(EmbeddedField... fields) throws Exception {
		return currencyPersistenceService.requestAllByCurrency(fields);
	}
	
	public CurrencyPageEvent requestByContainingCurrency(String currency) throws Exception {
				return currencyPersistenceService.requestByContainingCurrency(currency);
			}
	
	
}