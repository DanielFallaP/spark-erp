package co.com.cybersoft.purchase.tables.events.currencyCode;

import co.com.cybersoft.purchase.tables.core.domain.CurrencyCodeDetails;

/**
 * Event class for CurrencyCode
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CurrencyCodeModificationEvent {

	private CurrencyCodeDetails currencyCodeDetails;
	
	public CurrencyCodeModificationEvent(CurrencyCodeDetails currencyCodeDetails){
		this.currencyCodeDetails=currencyCodeDetails;
	}

	public CurrencyCodeDetails getCurrencyCodeDetails() {
		return currencyCodeDetails;
	}
	
}