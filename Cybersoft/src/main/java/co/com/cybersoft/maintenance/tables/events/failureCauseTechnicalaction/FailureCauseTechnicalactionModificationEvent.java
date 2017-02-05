package co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction;

import co.com.cybersoft.maintenance.tables.core.domain.FailureCauseTechnicalactionDetails;

/**
 * Event class for FailureCauseTechnicalaction
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class FailureCauseTechnicalactionModificationEvent {

	private FailureCauseTechnicalactionDetails failureCauseTechnicalactionDetails;
	
	public FailureCauseTechnicalactionModificationEvent(FailureCauseTechnicalactionDetails failureCauseTechnicalactionDetails){
		this.failureCauseTechnicalactionDetails=failureCauseTechnicalactionDetails;
	}

	public FailureCauseTechnicalactionDetails getFailureCauseTechnicalactionDetails() {
		return failureCauseTechnicalactionDetails;
	}
	
}