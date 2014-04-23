package co.com.cybersoft.events.corporation;

import co.com.cybersoft.core.domain.CorporationDetails;

/**
 * Event class for Corporation
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CorporationDetailsEvent {
	
	private CorporationDetails corporationDetails;
	
	public CorporationDetailsEvent(CorporationDetails corporationDetails){
		this.corporationDetails=corporationDetails;
	}

	public CorporationDetails getCorporationDetails() {
		return corporationDetails;
	}

}