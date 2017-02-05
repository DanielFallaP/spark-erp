package co.com.cybersoft.maintenance.tables.events.referenceOperation;

import co.com.cybersoft.maintenance.tables.core.domain.ReferenceOperationDetails;

/**
 * Event class for ReferenceOperation
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ReferenceOperationModificationEvent {

	private ReferenceOperationDetails referenceOperationDetails;
	
	public ReferenceOperationModificationEvent(ReferenceOperationDetails referenceOperationDetails){
		this.referenceOperationDetails=referenceOperationDetails;
	}

	public ReferenceOperationDetails getReferenceOperationDetails() {
		return referenceOperationDetails;
	}
	
}