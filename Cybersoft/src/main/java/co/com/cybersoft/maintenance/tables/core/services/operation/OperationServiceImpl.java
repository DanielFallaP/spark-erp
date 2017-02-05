package co.com.cybersoft.maintenance.tables.core.services.operation;

import co.com.cybersoft.maintenance.tables.events.operation.CreateOperationEvent;
import co.com.cybersoft.maintenance.tables.events.operation.OperationDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.operation.OperationPageEvent;
import co.com.cybersoft.maintenance.tables.events.operation.OperationModificationEvent;
import co.com.cybersoft.maintenance.tables.events.operation.RequestOperationDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.operation.RequestOperationPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.operation.OperationPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class OperationServiceImpl implements OperationService{

	private final OperationPersistenceService operationPersistenceService;
	
	public OperationServiceImpl(final OperationPersistenceService operationPersistenceService){
		this.operationPersistenceService=operationPersistenceService;
	}
	
	/**
	 */
	public OperationDetailsEvent createOperation(CreateOperationEvent event ) throws Exception{
		return operationPersistenceService.createOperation(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public OperationPageEvent requestOperationPage(RequestOperationPageEvent event) throws Exception{
		return operationPersistenceService.requestOperationPage(event);
	}

	public OperationDetailsEvent requestOperationDetails(RequestOperationDetailsEvent event ) throws Exception{
		return operationPersistenceService.requestOperationDetails(event);
	}

	public OperationDetailsEvent modifyOperation(OperationModificationEvent event) throws Exception {
		return operationPersistenceService.modifyOperation(event);
	}
	
	public OperationDetailsEvent requestOnlyRecord() throws Exception {
		return operationPersistenceService.getOnlyRecord();
	}
	
	public OperationPageEvent requestOperationFilterPage(RequestOperationPageEvent event) throws Exception {
		return operationPersistenceService.requestOperationFilterPage(event);
	}
	
	public OperationPageEvent requestOperationFilter(RequestOperationPageEvent event) throws Exception {
		return operationPersistenceService.requestOperationFilter(event);
	}
	

	public OperationPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return operationPersistenceService.requestAllByCompany(fields);
	}public OperationPageEvent requestAllByCodeOperation(EmbeddedField... fields) throws Exception {
		return operationPersistenceService.requestAllByCodeOperation(fields);
	}public OperationPageEvent requestAllByNameOperation(EmbeddedField... fields) throws Exception {
		return operationPersistenceService.requestAllByNameOperation(fields);
	}
	
	
	
}