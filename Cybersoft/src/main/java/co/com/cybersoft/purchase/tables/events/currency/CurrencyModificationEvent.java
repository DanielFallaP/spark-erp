package co.com.cybersoft.purchase.tables.events.currency;

import co.com.cybersoft.purchase.tables.core.domain.CurrencyDetails;

/**
 * Event class for Currency
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CurrencyModificationEvent {

	private CurrencyDetails currencyDetails;
	
	public CurrencyModificationEvent(CurrencyDetails currencyDetails){
		this.currencyDetails=currencyDetails;
	}

	public CurrencyDetails getCurrencyDetails() {
		return currencyDetails;
	}
	
}