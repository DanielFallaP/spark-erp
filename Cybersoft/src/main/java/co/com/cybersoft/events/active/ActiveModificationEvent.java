package co.com.cybersoft.events.active;

import co.com.cybersoft.core.domain.ActiveDetails;

/**
 * Event class for Active
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ActiveModificationEvent {

	private ActiveDetails activeDetails;
	
	public ActiveModificationEvent(ActiveDetails activeDetails){
		this.activeDetails=activeDetails;
	}

	public ActiveDetails getActiveDetails() {
		return activeDetails;
	}
	
}