package co.com.cybersoft.persistence.services.operationType;

import co.com.cybersoft.events.operationType.CreateOperationTypeEvent;
import co.com.cybersoft.events.operationType.OperationTypeDetailsEvent;
import co.com.cybersoft.events.operationType.OperationTypePageEvent;
import co.com.cybersoft.events.operationType.OperationTypeModificationEvent;
import co.com.cybersoft.events.operationType.RequestOperationTypeDetailsEvent;
import co.com.cybersoft.events.operationType.RequestOperationTypePageEvent;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface OperationTypePersistenceService {

	OperationTypeDetailsEvent createOperationType(CreateOperationTypeEvent event) throws Exception;

	OperationTypePageEvent requestOperationTypePage(RequestOperationTypePageEvent event) throws Exception;

	OperationTypeDetailsEvent requestOperationTypeDetails(RequestOperationTypeDetailsEvent event) throws Exception;
	
	OperationTypeDetailsEvent modifyOperationType(OperationTypeModificationEvent event) throws Exception;
	
	OperationTypePageEvent requestAll() throws Exception;
	
}