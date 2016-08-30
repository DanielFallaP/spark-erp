package co.com.cybersoft.purchase.tables.persistence.services.currencyCode;

import co.com.cybersoft.purchase.tables.events.currencyCode.CreateCurrencyCodeEvent;
import co.com.cybersoft.purchase.tables.events.currencyCode.CurrencyCodeDetailsEvent;
import co.com.cybersoft.purchase.tables.events.currencyCode.CurrencyCodePageEvent;
import co.com.cybersoft.purchase.tables.events.currencyCode.CurrencyCodeModificationEvent;
import co.com.cybersoft.purchase.tables.events.currencyCode.RequestCurrencyCodeDetailsEvent;
import co.com.cybersoft.purchase.tables.events.currencyCode.RequestCurrencyCodePageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface CurrencyCodePersistenceService {

	CurrencyCodeDetailsEvent createCurrencyCode(CreateCurrencyCodeEvent event) throws Exception;

	CurrencyCodePageEvent requestCurrencyCodePage(RequestCurrencyCodePageEvent event) throws Exception;

	CurrencyCodeDetailsEvent requestCurrencyCodeDetails(RequestCurrencyCodeDetailsEvent event) throws Exception;
	
	CurrencyCodeDetailsEvent modifyCurrencyCode(CurrencyCodeModificationEvent event) throws Exception;
	CurrencyCodePageEvent requestCurrencyCodeFilterPage(RequestCurrencyCodePageEvent event) throws Exception;
	CurrencyCodePageEvent requestCurrencyCodeFilter(RequestCurrencyCodePageEvent event) throws Exception;
	
	CurrencyCodePageEvent requestAllByCurrencyName(EmbeddedField... fields) throws Exception;
	CurrencyCodePageEvent requestAllByCountry(EmbeddedField... fields) throws Exception;
	CurrencyCodePageEvent requestAllByCurrency(EmbeddedField... fields) throws Exception;
	CurrencyCodePageEvent requestAllBySymbol(EmbeddedField... fields) throws Exception;
	CurrencyCodePageEvent requestAllByHex1(EmbeddedField... fields) throws Exception;
	CurrencyCodePageEvent requestAllByHex2(EmbeddedField... fields) throws Exception;
	CurrencyCodePageEvent requestAllByHex3(EmbeddedField... fields) throws Exception;

	
	CurrencyCodePageEvent requestByContainingCurrencyName(String currencyName) throws Exception;
	
	CurrencyCodeDetailsEvent getOnlyRecord() throws Exception;
	
}