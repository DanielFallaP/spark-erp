package co.com.cybersoft.maintenance.tables.core.services.reference;

import co.com.cybersoft.maintenance.tables.events.reference.CreateReferenceEvent;
import co.com.cybersoft.maintenance.tables.events.reference.ReferenceDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.reference.ReferencePageEvent;
import co.com.cybersoft.maintenance.tables.events.reference.ReferenceModificationEvent;
import co.com.cybersoft.maintenance.tables.events.reference.RequestReferenceDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.reference.RequestReferencePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.reference.ReferencePersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ReferenceServiceImpl implements ReferenceService{

	private final ReferencePersistenceService referencePersistenceService;
	
	public ReferenceServiceImpl(final ReferencePersistenceService referencePersistenceService){
		this.referencePersistenceService=referencePersistenceService;
	}
	
	/**
	 */
	public ReferenceDetailsEvent createReference(CreateReferenceEvent event ) throws Exception{
		return referencePersistenceService.createReference(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public ReferencePageEvent requestReferencePage(RequestReferencePageEvent event) throws Exception{
		return referencePersistenceService.requestReferencePage(event);
	}

	public ReferenceDetailsEvent requestReferenceDetails(RequestReferenceDetailsEvent event ) throws Exception{
		return referencePersistenceService.requestReferenceDetails(event);
	}

	public ReferenceDetailsEvent modifyReference(ReferenceModificationEvent event) throws Exception {
		return referencePersistenceService.modifyReference(event);
	}
	
	public ReferenceDetailsEvent requestOnlyRecord() throws Exception {
		return referencePersistenceService.getOnlyRecord();
	}
	
	public ReferencePageEvent requestReferenceFilterPage(RequestReferencePageEvent event) throws Exception {
		return referencePersistenceService.requestReferenceFilterPage(event);
	}
	
	public ReferencePageEvent requestReferenceFilter(RequestReferencePageEvent event) throws Exception {
		return referencePersistenceService.requestReferenceFilter(event);
	}
	

	public ReferencePageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return referencePersistenceService.requestAllByCompany(fields);
	}public ReferencePageEvent requestAllByCodeReference(EmbeddedField... fields) throws Exception {
		return referencePersistenceService.requestAllByCodeReference(fields);
	}public ReferencePageEvent requestAllByNameReference(EmbeddedField... fields) throws Exception {
		return referencePersistenceService.requestAllByNameReference(fields);
	}
	
	
	
}