package co.com.cybersoft.purchase.tables.events.exchangeRate;

import co.com.cybersoft.purchase.tables.core.domain.ExchangeRateDetails;

/**
 * Event class for ExchangeRate
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ExchangeRateModificationEvent {

	private ExchangeRateDetails exchangeRateDetails;
	
	public ExchangeRateModificationEvent(ExchangeRateDetails exchangeRateDetails){
		this.exchangeRateDetails=exchangeRateDetails;
	}

	public ExchangeRateDetails getExchangeRateDetails() {
		return exchangeRateDetails;
	}
	
}