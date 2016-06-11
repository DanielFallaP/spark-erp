package co.com.cybersoft.purchase.tables.events.exchangeRate;

import co.com.cybersoft.purchase.tables.core.domain.ExchangeRateDetails;

/**
 * Event class for ExchangeRate
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateExchangeRateEvent {
		
	private ExchangeRateDetails exchangeRateDetails;
	
	public CreateExchangeRateEvent(ExchangeRateDetails exchangeRateDetails){
		this.exchangeRateDetails=exchangeRateDetails;
	}

	public ExchangeRateDetails getExchangeRateDetails() {
		return exchangeRateDetails;
	}
	
	
}