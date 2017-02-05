package co.com.cybersoft.maintenance.tables.events.reference;

import co.com.cybersoft.maintenance.tables.core.domain.ReferenceDetails;

/**
 * Event class for Reference
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ReferenceModificationEvent {

	private ReferenceDetails referenceDetails;
	
	public ReferenceModificationEvent(ReferenceDetails referenceDetails){
		this.referenceDetails=referenceDetails;
	}

	public ReferenceDetails getReferenceDetails() {
		return referenceDetails;
	}
	
}