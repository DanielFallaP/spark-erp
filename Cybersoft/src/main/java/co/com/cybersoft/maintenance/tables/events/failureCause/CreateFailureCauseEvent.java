package co.com.cybersoft.maintenance.tables.events.failureCause;

import co.com.cybersoft.maintenance.tables.core.domain.FailureCauseDetails;

/**
 * Event class for FailureCause
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateFailureCauseEvent {
		
	private FailureCauseDetails failureCauseDetails;
	
	public CreateFailureCauseEvent(FailureCauseDetails failureCauseDetails){
		this.failureCauseDetails=failureCauseDetails;
	}

	public FailureCauseDetails getFailureCauseDetails() {
		return failureCauseDetails;
	}
	
	
}