package co.com.cybersoft.maintenance.tables.core.services.operation;

import co.com.cybersoft.maintenance.tables.events.operation.CreateOperationEvent;
import co.com.cybersoft.maintenance.tables.events.operation.OperationDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.operation.OperationPageEvent;
import co.com.cybersoft.maintenance.tables.events.operation.OperationModificationEvent;
import co.com.cybersoft.maintenance.tables.events.operation.RequestOperationDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.operation.RequestOperationPageEvent;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface OperationService {
	OperationDetailsEvent requestOnlyRecord() throws Exception;

	OperationDetailsEvent createOperation(CreateOperationEvent event ) throws Exception;
	
	OperationPageEvent requestOperationPage(RequestOperationPageEvent event) throws Exception;

	OperationDetailsEvent requestOperationDetails(RequestOperationDetailsEvent event ) throws Exception;

	OperationDetailsEvent modifyOperation(OperationModificationEvent event) throws Exception;
	
	OperationPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	OperationPageEvent requestAllByCodeOperation(EmbeddedField... fields) throws Exception;
	OperationPageEvent requestAllByNameOperation(EmbeddedField... fields) throws Exception;

	
	
	OperationPageEvent requestOperationFilterPage(RequestOperationPageEvent event) throws Exception;

	OperationPageEvent requestOperationFilter(RequestOperationPageEvent event) throws Exception;
	
}