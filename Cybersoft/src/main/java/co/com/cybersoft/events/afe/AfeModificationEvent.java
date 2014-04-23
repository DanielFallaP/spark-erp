package co.com.cybersoft.events.afe;

import co.com.cybersoft.core.domain.AfeDetails;

/**
 * Event class for Afe
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class AfeModificationEvent {

	private AfeDetails afeDetails;
	
	public AfeModificationEvent(AfeDetails afeDetails){
		this.afeDetails=afeDetails;
	}

	public AfeDetails getAfeDetails() {
		return afeDetails;
	}
	
}