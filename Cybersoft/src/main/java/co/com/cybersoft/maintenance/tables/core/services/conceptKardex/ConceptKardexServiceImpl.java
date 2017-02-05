package co.com.cybersoft.maintenance.tables.core.services.conceptKardex;

import co.com.cybersoft.maintenance.tables.events.conceptKardex.CreateConceptKardexEvent;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.ConceptKardexDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.ConceptKardexPageEvent;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.ConceptKardexModificationEvent;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.RequestConceptKardexDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.RequestConceptKardexPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.conceptKardex.ConceptKardexPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ConceptKardexServiceImpl implements ConceptKardexService{

	private final ConceptKardexPersistenceService conceptKardexPersistenceService;
	
	public ConceptKardexServiceImpl(final ConceptKardexPersistenceService conceptKardexPersistenceService){
		this.conceptKardexPersistenceService=conceptKardexPersistenceService;
	}
	
	/**
	 */
	public ConceptKardexDetailsEvent createConceptKardex(CreateConceptKardexEvent event ) throws Exception{
		return conceptKardexPersistenceService.createConceptKardex(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public ConceptKardexPageEvent requestConceptKardexPage(RequestConceptKardexPageEvent event) throws Exception{
		return conceptKardexPersistenceService.requestConceptKardexPage(event);
	}

	public ConceptKardexDetailsEvent requestConceptKardexDetails(RequestConceptKardexDetailsEvent event ) throws Exception{
		return conceptKardexPersistenceService.requestConceptKardexDetails(event);
	}

	public ConceptKardexDetailsEvent modifyConceptKardex(ConceptKardexModificationEvent event) throws Exception {
		return conceptKardexPersistenceService.modifyConceptKardex(event);
	}
	
	public ConceptKardexDetailsEvent requestOnlyRecord() throws Exception {
		return conceptKardexPersistenceService.getOnlyRecord();
	}
	
	public ConceptKardexPageEvent requestConceptKardexFilterPage(RequestConceptKardexPageEvent event) throws Exception {
		return conceptKardexPersistenceService.requestConceptKardexFilterPage(event);
	}
	
	public ConceptKardexPageEvent requestConceptKardexFilter(RequestConceptKardexPageEvent event) throws Exception {
		return conceptKardexPersistenceService.requestConceptKardexFilter(event);
	}
	

	public ConceptKardexPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return conceptKardexPersistenceService.requestAllByCompany(fields);
	}public ConceptKardexPageEvent requestAllByStore(EmbeddedField... fields) throws Exception {
		return conceptKardexPersistenceService.requestAllByStore(fields);
	}public ConceptKardexPageEvent requestAllByTypeConceptKardex(EmbeddedField... fields) throws Exception {
		return conceptKardexPersistenceService.requestAllByTypeConceptKardex(fields);
	}
	
	
	
}