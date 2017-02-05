package co.com.cybersoft.maintenance.tables.events.operation;

import co.com.cybersoft.maintenance.tables.core.domain.OperationDetails;

/**
 * Event class for Operation
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class OperationDetailsEvent {
	
	private OperationDetails operationDetails;
	
	public OperationDetailsEvent(OperationDetails operationDetails){
		this.operationDetails=operationDetails;
	}

	public OperationDetails getOperationDetails() {
		return operationDetails;
	}

}