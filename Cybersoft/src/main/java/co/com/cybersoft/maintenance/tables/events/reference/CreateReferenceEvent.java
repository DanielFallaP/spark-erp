package co.com.cybersoft.maintenance.tables.events.reference;

import co.com.cybersoft.maintenance.tables.core.domain.ReferenceDetails;

/**
 * Event class for Reference
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateReferenceEvent {
		
	private ReferenceDetails referenceDetails;
	
	public CreateReferenceEvent(ReferenceDetails referenceDetails){
		this.referenceDetails=referenceDetails;
	}

	public ReferenceDetails getReferenceDetails() {
		return referenceDetails;
	}
	
	
}