package co.com.cybersoft.maintenance.tables.events.state;

import co.com.cybersoft.maintenance.tables.core.domain.StateDetails;

/**
 * Event class for State
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class StateDetailsEvent {
	
	private StateDetails stateDetails;
	
	public StateDetailsEvent(StateDetails stateDetails){
		this.stateDetails=stateDetails;
	}

	public StateDetails getStateDetails() {
		return stateDetails;
	}

}