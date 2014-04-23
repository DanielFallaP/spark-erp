package co.com.cybersoft.events.operationType;

import co.com.cybersoft.core.domain.OperationTypeDetails;

/**
 * Event class for OperationType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateOperationTypeEvent {
		
	private OperationTypeDetails operationTypeDetails;
	
	public CreateOperationTypeEvent(OperationTypeDetails operationTypeDetails){
		this.operationTypeDetails=operationTypeDetails;
	}

	public OperationTypeDetails getOperationTypeDetails() {
		return operationTypeDetails;
	}
	
	
}