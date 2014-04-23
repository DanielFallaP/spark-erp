package co.com.cybersoft.events.corporation;

import co.com.cybersoft.core.domain.CorporationDetails;

/**
 * Event class for Corporation
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CorporationModificationEvent {

	private CorporationDetails corporationDetails;
	
	public CorporationModificationEvent(CorporationDetails corporationDetails){
		this.corporationDetails=corporationDetails;
	}

	public CorporationDetails getCorporationDetails() {
		return corporationDetails;
	}
	
}