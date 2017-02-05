package co.com.cybersoft.maintenance.tables.events.coin;

import co.com.cybersoft.maintenance.tables.core.domain.CoinDetails;

/**
 * Event class for Coin
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateCoinEvent {
		
	private CoinDetails coinDetails;
	
	public CreateCoinEvent(CoinDetails coinDetails){
		this.coinDetails=coinDetails;
	}

	public CoinDetails getCoinDetails() {
		return coinDetails;
	}
	
	
}