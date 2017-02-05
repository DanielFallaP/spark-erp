package co.com.cybersoft.maintenance.tables.persistence.services.referenceOperation;

import co.com.cybersoft.maintenance.tables.events.referenceOperation.CreateReferenceOperationEvent;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.ReferenceOperationDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.ReferenceOperationPageEvent;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.ReferenceOperationModificationEvent;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.RequestReferenceOperationDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.RequestReferenceOperationPageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface ReferenceOperationPersistenceService {

	ReferenceOperationDetailsEvent createReferenceOperation(CreateReferenceOperationEvent event) throws Exception;

	ReferenceOperationPageEvent requestReferenceOperationPage(RequestReferenceOperationPageEvent event) throws Exception;

	ReferenceOperationDetailsEvent requestReferenceOperationDetails(RequestReferenceOperationDetailsEvent event) throws Exception;
	
	ReferenceOperationDetailsEvent modifyReferenceOperation(ReferenceOperationModificationEvent event) throws Exception;
	ReferenceOperationPageEvent requestReferenceOperationFilterPage(RequestReferenceOperationPageEvent event) throws Exception;
	ReferenceOperationPageEvent requestReferenceOperationFilter(RequestReferenceOperationPageEvent event) throws Exception;
	
	ReferenceOperationPageEvent requestAllByReference(EmbeddedField... fields) throws Exception;
	ReferenceOperationPageEvent requestAllByOperation(EmbeddedField... fields) throws Exception;

	
	
	ReferenceOperationDetailsEvent getOnlyRecord() throws Exception;
	
}