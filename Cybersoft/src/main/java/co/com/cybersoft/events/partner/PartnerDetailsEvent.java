package co.com.cybersoft.events.partner;

import co.com.cybersoft.core.domain.PartnerDetails;

/**
 * Event class for Partner
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class PartnerDetailsEvent {
	
	private PartnerDetails partnerDetails;
	
	public PartnerDetailsEvent(PartnerDetails partnerDetails){
		this.partnerDetails=partnerDetails;
	}

	public PartnerDetails getPartnerDetails() {
		return partnerDetails;
	}

}