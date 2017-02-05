package co.com.cybersoft.maintenance.tables.events.failureCause;

import co.com.cybersoft.maintenance.tables.core.domain.FailureCauseDetails;

/**
 * Event class for FailureCause
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class FailureCauseDetailsEvent {
	
	private FailureCauseDetails failureCauseDetails;
	
	public FailureCauseDetailsEvent(FailureCauseDetails failureCauseDetails){
		this.failureCauseDetails=failureCauseDetails;
	}

	public FailureCauseDetails getFailureCauseDetails() {
		return failureCauseDetails;
	}

}