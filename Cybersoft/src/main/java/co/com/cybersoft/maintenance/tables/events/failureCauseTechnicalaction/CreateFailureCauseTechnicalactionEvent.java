package co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction;

import co.com.cybersoft.maintenance.tables.core.domain.FailureCauseTechnicalactionDetails;

/**
 * Event class for FailureCauseTechnicalaction
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateFailureCauseTechnicalactionEvent {
		
	private FailureCauseTechnicalactionDetails failureCauseTechnicalactionDetails;
	
	public CreateFailureCauseTechnicalactionEvent(FailureCauseTechnicalactionDetails failureCauseTechnicalactionDetails){
		this.failureCauseTechnicalactionDetails=failureCauseTechnicalactionDetails;
	}

	public FailureCauseTechnicalactionDetails getFailureCauseTechnicalactionDetails() {
		return failureCauseTechnicalactionDetails;
	}
	
	
}