package co.com.cybersoft.purchase.tables.events.continent;

import co.com.cybersoft.purchase.tables.core.domain.ContinentDetails;

/**
 * Event class for Continent
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ContinentDetailsEvent {
	
	private ContinentDetails continentDetails;
	
	public ContinentDetailsEvent(ContinentDetails continentDetails){
		this.continentDetails=continentDetails;
	}

	public ContinentDetails getContinentDetails() {
		return continentDetails;
	}

}