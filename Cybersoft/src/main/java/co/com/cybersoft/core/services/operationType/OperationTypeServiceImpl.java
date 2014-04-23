package co.com.cybersoft.core.services.operationType;

import java.util.Date;

import co.com.cybersoft.events.operationType.CreateOperationTypeEvent;
import co.com.cybersoft.events.operationType.OperationTypeDetailsEvent;
import co.com.cybersoft.events.operationType.OperationTypePageEvent;
import co.com.cybersoft.events.operationType.OperationTypeModificationEvent;
import co.com.cybersoft.events.operationType.RequestOperationTypeDetailsEvent;
import co.com.cybersoft.events.operationType.RequestOperationTypePageEvent;
import co.com.cybersoft.persistence.services.operationType.OperationTypePersistenceService;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class OperationTypeServiceImpl implements OperationTypeService{

	private final OperationTypePersistenceService operationTypePersistenceService;
	
	public OperationTypeServiceImpl(final OperationTypePersistenceService operationTypePersistenceService){
		this.operationTypePersistenceService=operationTypePersistenceService;
	}
	
	/**
	 */
	@Override
	public OperationTypeDetailsEvent createOperationType(CreateOperationTypeEvent event ) throws Exception{
		return operationTypePersistenceService.createOperationType(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	@Override
	public OperationTypePageEvent requestOperationTypePage(RequestOperationTypePageEvent event) throws Exception{
		return operationTypePersistenceService.requestOperationTypePage(event);
	}

	@Override
	public OperationTypeDetailsEvent requestOperationTypeDetails(RequestOperationTypeDetailsEvent event ) throws Exception{
		return operationTypePersistenceService.requestOperationTypeDetails(event);
	}

	@Override
	public OperationTypeDetailsEvent modifyOperationType(OperationTypeModificationEvent event) throws Exception {
		return operationTypePersistenceService.modifyOperationType(event);
	}
	
	@Override
	public OperationTypePageEvent requestAll() throws Exception {
		return operationTypePersistenceService.requestAll();
	}

}