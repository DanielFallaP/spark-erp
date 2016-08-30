package co.com.cybersoft.purchase.tables.core.services.currencyCode;

import co.com.cybersoft.purchase.tables.events.currencyCode.CreateCurrencyCodeEvent;
import co.com.cybersoft.purchase.tables.events.currencyCode.CurrencyCodeDetailsEvent;
import co.com.cybersoft.purchase.tables.events.currencyCode.CurrencyCodePageEvent;
import co.com.cybersoft.purchase.tables.events.currencyCode.CurrencyCodeModificationEvent;
import co.com.cybersoft.purchase.tables.events.currencyCode.RequestCurrencyCodeDetailsEvent;
import co.com.cybersoft.purchase.tables.events.currencyCode.RequestCurrencyCodePageEvent;
import co.com.cybersoft.purchase.tables.persistence.services.currencyCode.CurrencyCodePersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CurrencyCodeServiceImpl implements CurrencyCodeService{

	private final CurrencyCodePersistenceService currencyCodePersistenceService;
	
	public CurrencyCodeServiceImpl(final CurrencyCodePersistenceService currencyCodePersistenceService){
		this.currencyCodePersistenceService=currencyCodePersistenceService;
	}
	
	/**
	 */
	public CurrencyCodeDetailsEvent createCurrencyCode(CreateCurrencyCodeEvent event ) throws Exception{
		return currencyCodePersistenceService.createCurrencyCode(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public CurrencyCodePageEvent requestCurrencyCodePage(RequestCurrencyCodePageEvent event) throws Exception{
		return currencyCodePersistenceService.requestCurrencyCodePage(event);
	}

	public CurrencyCodeDetailsEvent requestCurrencyCodeDetails(RequestCurrencyCodeDetailsEvent event ) throws Exception{
		return currencyCodePersistenceService.requestCurrencyCodeDetails(event);
	}

	public CurrencyCodeDetailsEvent modifyCurrencyCode(CurrencyCodeModificationEvent event) throws Exception {
		return currencyCodePersistenceService.modifyCurrencyCode(event);
	}
	
	public CurrencyCodeDetailsEvent requestOnlyRecord() throws Exception {
		return currencyCodePersistenceService.getOnlyRecord();
	}
	
	public CurrencyCodePageEvent requestCurrencyCodeFilterPage(RequestCurrencyCodePageEvent event) throws Exception {
		return currencyCodePersistenceService.requestCurrencyCodeFilterPage(event);
	}
	
	public CurrencyCodePageEvent requestCurrencyCodeFilter(RequestCurrencyCodePageEvent event) throws Exception {
		return currencyCodePersistenceService.requestCurrencyCodeFilter(event);
	}
	

	public CurrencyCodePageEvent requestAllByCurrencyName(EmbeddedField... fields) throws Exception {
		return currencyCodePersistenceService.requestAllByCurrencyName(fields);
	}public CurrencyCodePageEvent requestAllByCountry(EmbeddedField... fields) throws Exception {
		return currencyCodePersistenceService.requestAllByCountry(fields);
	}public CurrencyCodePageEvent requestAllByCurrency(EmbeddedField... fields) throws Exception {
		return currencyCodePersistenceService.requestAllByCurrency(fields);
	}public CurrencyCodePageEvent requestAllBySymbol(EmbeddedField... fields) throws Exception {
		return currencyCodePersistenceService.requestAllBySymbol(fields);
	}public CurrencyCodePageEvent requestAllByHex1(EmbeddedField... fields) throws Exception {
		return currencyCodePersistenceService.requestAllByHex1(fields);
	}public CurrencyCodePageEvent requestAllByHex2(EmbeddedField... fields) throws Exception {
		return currencyCodePersistenceService.requestAllByHex2(fields);
	}public CurrencyCodePageEvent requestAllByHex3(EmbeddedField... fields) throws Exception {
		return currencyCodePersistenceService.requestAllByHex3(fields);
	}
	
	public CurrencyCodePageEvent requestByContainingCurrencyName(String currencyName) throws Exception {
				return currencyCodePersistenceService.requestByContainingCurrencyName(currencyName);
			}
	
	
}