package co.com.cybersoft.maintenance.tables.core.services.referenceOperation;

import co.com.cybersoft.maintenance.tables.events.referenceOperation.CreateReferenceOperationEvent;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.ReferenceOperationDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.ReferenceOperationPageEvent;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.ReferenceOperationModificationEvent;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.RequestReferenceOperationDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.RequestReferenceOperationPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.referenceOperation.ReferenceOperationPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ReferenceOperationServiceImpl implements ReferenceOperationService{

	private final ReferenceOperationPersistenceService referenceOperationPersistenceService;
	
	public ReferenceOperationServiceImpl(final ReferenceOperationPersistenceService referenceOperationPersistenceService){
		this.referenceOperationPersistenceService=referenceOperationPersistenceService;
	}
	
	/**
	 */
	public ReferenceOperationDetailsEvent createReferenceOperation(CreateReferenceOperationEvent event ) throws Exception{
		return referenceOperationPersistenceService.createReferenceOperation(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public ReferenceOperationPageEvent requestReferenceOperationPage(RequestReferenceOperationPageEvent event) throws Exception{
		return referenceOperationPersistenceService.requestReferenceOperationPage(event);
	}

	public ReferenceOperationDetailsEvent requestReferenceOperationDetails(RequestReferenceOperationDetailsEvent event ) throws Exception{
		return referenceOperationPersistenceService.requestReferenceOperationDetails(event);
	}

	public ReferenceOperationDetailsEvent modifyReferenceOperation(ReferenceOperationModificationEvent event) throws Exception {
		return referenceOperationPersistenceService.modifyReferenceOperation(event);
	}
	
	public ReferenceOperationDetailsEvent requestOnlyRecord() throws Exception {
		return referenceOperationPersistenceService.getOnlyRecord();
	}
	
	public ReferenceOperationPageEvent requestReferenceOperationFilterPage(RequestReferenceOperationPageEvent event) throws Exception {
		return referenceOperationPersistenceService.requestReferenceOperationFilterPage(event);
	}
	
	public ReferenceOperationPageEvent requestReferenceOperationFilter(RequestReferenceOperationPageEvent event) throws Exception {
		return referenceOperationPersistenceService.requestReferenceOperationFilter(event);
	}
	

	public ReferenceOperationPageEvent requestAllByReference(EmbeddedField... fields) throws Exception {
		return referenceOperationPersistenceService.requestAllByReference(fields);
	}public ReferenceOperationPageEvent requestAllByOperation(EmbeddedField... fields) throws Exception {
		return referenceOperationPersistenceService.requestAllByOperation(fields);
	}
	
	
	
}