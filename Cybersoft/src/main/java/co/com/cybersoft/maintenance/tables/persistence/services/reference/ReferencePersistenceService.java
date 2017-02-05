package co.com.cybersoft.maintenance.tables.persistence.services.reference;

import co.com.cybersoft.maintenance.tables.events.reference.CreateReferenceEvent;
import co.com.cybersoft.maintenance.tables.events.reference.ReferenceDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.reference.ReferencePageEvent;
import co.com.cybersoft.maintenance.tables.events.reference.ReferenceModificationEvent;
import co.com.cybersoft.maintenance.tables.events.reference.RequestReferenceDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.reference.RequestReferencePageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface ReferencePersistenceService {

	ReferenceDetailsEvent createReference(CreateReferenceEvent event) throws Exception;

	ReferencePageEvent requestReferencePage(RequestReferencePageEvent event) throws Exception;

	ReferenceDetailsEvent requestReferenceDetails(RequestReferenceDetailsEvent event) throws Exception;
	
	ReferenceDetailsEvent modifyReference(ReferenceModificationEvent event) throws Exception;
	ReferencePageEvent requestReferenceFilterPage(RequestReferencePageEvent event) throws Exception;
	ReferencePageEvent requestReferenceFilter(RequestReferencePageEvent event) throws Exception;
	
	ReferencePageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	ReferencePageEvent requestAllByCodeReference(EmbeddedField... fields) throws Exception;
	ReferencePageEvent requestAllByNameReference(EmbeddedField... fields) throws Exception;

	
	
	ReferenceDetailsEvent getOnlyRecord() throws Exception;
	
}