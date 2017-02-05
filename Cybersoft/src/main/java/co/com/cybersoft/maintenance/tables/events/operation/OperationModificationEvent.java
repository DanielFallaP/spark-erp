package co.com.cybersoft.maintenance.tables.events.operation;

import co.com.cybersoft.maintenance.tables.core.domain.OperationDetails;

/**
 * Event class for Operation
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class OperationModificationEvent {

	private OperationDetails operationDetails;
	
	public OperationModificationEvent(OperationDetails operationDetails){
		this.operationDetails=operationDetails;
	}

	public OperationDetails getOperationDetails() {
		return operationDetails;
	}
	
}