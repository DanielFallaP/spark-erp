package co.com.cybersoft.maintenance.tables.core.services.otherConcepts;

import co.com.cybersoft.maintenance.tables.events.otherConcepts.CreateOtherConceptsEvent;
import co.com.cybersoft.maintenance.tables.events.otherConcepts.OtherConceptsDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.otherConcepts.OtherConceptsPageEvent;
import co.com.cybersoft.maintenance.tables.events.otherConcepts.OtherConceptsModificationEvent;
import co.com.cybersoft.maintenance.tables.events.otherConcepts.RequestOtherConceptsDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.otherConcepts.RequestOtherConceptsPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.otherConcepts.OtherConceptsPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class OtherConceptsServiceImpl implements OtherConceptsService{

	private final OtherConceptsPersistenceService otherConceptsPersistenceService;
	
	public OtherConceptsServiceImpl(final OtherConceptsPersistenceService otherConceptsPersistenceService){
		this.otherConceptsPersistenceService=otherConceptsPersistenceService;
	}
	
	/**
	 */
	public OtherConceptsDetailsEvent createOtherConcepts(CreateOtherConceptsEvent event ) throws Exception{
		return otherConceptsPersistenceService.createOtherConcepts(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public OtherConceptsPageEvent requestOtherConceptsPage(RequestOtherConceptsPageEvent event) throws Exception{
		return otherConceptsPersistenceService.requestOtherConceptsPage(event);
	}

	public OtherConceptsDetailsEvent requestOtherConceptsDetails(RequestOtherConceptsDetailsEvent event ) throws Exception{
		return otherConceptsPersistenceService.requestOtherConceptsDetails(event);
	}

	public OtherConceptsDetailsEvent modifyOtherConcepts(OtherConceptsModificationEvent event) throws Exception {
		return otherConceptsPersistenceService.modifyOtherConcepts(event);
	}
	
	public OtherConceptsDetailsEvent requestOnlyRecord() throws Exception {
		return otherConceptsPersistenceService.getOnlyRecord();
	}
	
	public OtherConceptsPageEvent requestOtherConceptsFilterPage(RequestOtherConceptsPageEvent event) throws Exception {
		return otherConceptsPersistenceService.requestOtherConceptsFilterPage(event);
	}
	
	public OtherConceptsPageEvent requestOtherConceptsFilter(RequestOtherConceptsPageEvent event) throws Exception {
		return otherConceptsPersistenceService.requestOtherConceptsFilter(event);
	}
	

	public OtherConceptsPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return otherConceptsPersistenceService.requestAllByCompany(fields);
	}public OtherConceptsPageEvent requestAllByNameOtherconcepts(EmbeddedField... fields) throws Exception {
		return otherConceptsPersistenceService.requestAllByNameOtherconcepts(fields);
	}public OtherConceptsPageEvent requestAllByUnitMeasure(EmbeddedField... fields) throws Exception {
		return otherConceptsPersistenceService.requestAllByUnitMeasure(fields);
	}public OtherConceptsPageEvent requestAllByTypeWork(EmbeddedField... fields) throws Exception {
		return otherConceptsPersistenceService.requestAllByTypeWork(fields);
	}
	
	
	
}