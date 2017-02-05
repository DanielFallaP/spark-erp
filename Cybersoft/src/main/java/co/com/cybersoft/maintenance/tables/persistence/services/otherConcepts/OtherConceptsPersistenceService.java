package co.com.cybersoft.maintenance.tables.persistence.services.otherConcepts;

import co.com.cybersoft.maintenance.tables.events.otherConcepts.CreateOtherConceptsEvent;
import co.com.cybersoft.maintenance.tables.events.otherConcepts.OtherConceptsDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.otherConcepts.OtherConceptsPageEvent;
import co.com.cybersoft.maintenance.tables.events.otherConcepts.OtherConceptsModificationEvent;
import co.com.cybersoft.maintenance.tables.events.otherConcepts.RequestOtherConceptsDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.otherConcepts.RequestOtherConceptsPageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface OtherConceptsPersistenceService {

	OtherConceptsDetailsEvent createOtherConcepts(CreateOtherConceptsEvent event) throws Exception;

	OtherConceptsPageEvent requestOtherConceptsPage(RequestOtherConceptsPageEvent event) throws Exception;

	OtherConceptsDetailsEvent requestOtherConceptsDetails(RequestOtherConceptsDetailsEvent event) throws Exception;
	
	OtherConceptsDetailsEvent modifyOtherConcepts(OtherConceptsModificationEvent event) throws Exception;
	OtherConceptsPageEvent requestOtherConceptsFilterPage(RequestOtherConceptsPageEvent event) throws Exception;
	OtherConceptsPageEvent requestOtherConceptsFilter(RequestOtherConceptsPageEvent event) throws Exception;
	
	OtherConceptsPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	OtherConceptsPageEvent requestAllByNameOtherconcepts(EmbeddedField... fields) throws Exception;
	OtherConceptsPageEvent requestAllByUnitMeasure(EmbeddedField... fields) throws Exception;
	OtherConceptsPageEvent requestAllByTypeWork(EmbeddedField... fields) throws Exception;

	
	
	OtherConceptsDetailsEvent getOnlyRecord() throws Exception;
	
}