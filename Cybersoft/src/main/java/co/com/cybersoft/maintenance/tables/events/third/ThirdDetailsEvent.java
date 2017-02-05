package co.com.cybersoft.maintenance.tables.events.third;

import co.com.cybersoft.maintenance.tables.core.domain.ThirdDetails;

/**
 * Event class for Third
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ThirdDetailsEvent {
	
	private ThirdDetails thirdDetails;
	
	public ThirdDetailsEvent(ThirdDetails thirdDetails){
		this.thirdDetails=thirdDetails;
	}

	public ThirdDetails getThirdDetails() {
		return thirdDetails;
	}

}