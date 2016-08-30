package co.com.cybersoft.purchase.tables.core.services.currency;

import co.com.cybersoft.purchase.tables.events.currency.CreateCurrencyEvent;
import co.com.cybersoft.purchase.tables.events.currency.CurrencyDetailsEvent;
import co.com.cybersoft.purchase.tables.events.currency.CurrencyPageEvent;
import co.com.cybersoft.purchase.tables.events.currency.CurrencyModificationEvent;
import co.com.cybersoft.purchase.tables.events.currency.RequestCurrencyDetailsEvent;
import co.com.cybersoft.purchase.tables.events.currency.RequestCurrencyPageEvent;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface CurrencyService {
	CurrencyDetailsEvent requestOnlyRecord() throws Exception;

	CurrencyDetailsEvent createCurrency(CreateCurrencyEvent event ) throws Exception;
	
	CurrencyPageEvent requestCurrencyPage(RequestCurrencyPageEvent event) throws Exception;

	CurrencyDetailsEvent requestCurrencyDetails(RequestCurrencyDetailsEvent event ) throws Exception;

	CurrencyDetailsEvent modifyCurrency(CurrencyModificationEvent event) throws Exception;
	
	CurrencyPageEvent requestAllByCode(EmbeddedField... fields) throws Exception;
	CurrencyPageEvent requestAllByCurrency(EmbeddedField... fields) throws Exception;

	
	CurrencyPageEvent requestByContainingCurrency(String currency) throws Exception;
	
	CurrencyPageEvent requestCurrencyFilterPage(RequestCurrencyPageEvent event) throws Exception;

	CurrencyPageEvent requestCurrencyFilter(RequestCurrencyPageEvent event) throws Exception;
	
}