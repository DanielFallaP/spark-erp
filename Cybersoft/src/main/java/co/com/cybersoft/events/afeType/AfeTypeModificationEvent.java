package co.com.cybersoft.events.afeType;

import co.com.cybersoft.core.domain.AfeTypeDetails;

/**
 * Event class for AfeType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class AfeTypeModificationEvent {

	private AfeTypeDetails afeTypeDetails;
	
	public AfeTypeModificationEvent(AfeTypeDetails afeTypeDetails){
		this.afeTypeDetails=afeTypeDetails;
	}

	public AfeTypeDetails getAfeTypeDetails() {
		return afeTypeDetails;
	}
	
}