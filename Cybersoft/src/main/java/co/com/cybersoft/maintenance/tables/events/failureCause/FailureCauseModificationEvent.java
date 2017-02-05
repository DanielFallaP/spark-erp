package co.com.cybersoft.maintenance.tables.events.failureCause;

import co.com.cybersoft.maintenance.tables.core.domain.FailureCauseDetails;

/**
 * Event class for FailureCause
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class FailureCauseModificationEvent {

	private FailureCauseDetails failureCauseDetails;
	
	public FailureCauseModificationEvent(FailureCauseDetails failureCauseDetails){
		this.failureCauseDetails=failureCauseDetails;
	}

	public FailureCauseDetails getFailureCauseDetails() {
		return failureCauseDetails;
	}
	
}