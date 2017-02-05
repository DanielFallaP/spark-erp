package co.com.cybersoft.maintenance.tables.events.coin;

import co.com.cybersoft.maintenance.tables.core.domain.CoinDetails;

/**
 * Event class for Coin
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CoinDetailsEvent {
	
	private CoinDetails coinDetails;
	
	public CoinDetailsEvent(CoinDetails coinDetails){
		this.coinDetails=coinDetails;
	}

	public CoinDetails getCoinDetails() {
		return coinDetails;
	}

}