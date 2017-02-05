package co.com.cybersoft.maintenance.tables.core.services.responsible;

import co.com.cybersoft.maintenance.tables.events.responsible.CreateResponsibleEvent;
import co.com.cybersoft.maintenance.tables.events.responsible.ResponsibleDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.responsible.ResponsiblePageEvent;
import co.com.cybersoft.maintenance.tables.events.responsible.ResponsibleModificationEvent;
import co.com.cybersoft.maintenance.tables.events.responsible.RequestResponsibleDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.responsible.RequestResponsiblePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.responsible.ResponsiblePersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ResponsibleServiceImpl implements ResponsibleService{

	private final ResponsiblePersistenceService responsiblePersistenceService;
	
	public ResponsibleServiceImpl(final ResponsiblePersistenceService responsiblePersistenceService){
		this.responsiblePersistenceService=responsiblePersistenceService;
	}
	
	/**
	 */
	public ResponsibleDetailsEvent createResponsible(CreateResponsibleEvent event ) throws Exception{
		return responsiblePersistenceService.createResponsible(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public ResponsiblePageEvent requestResponsiblePage(RequestResponsiblePageEvent event) throws Exception{
		return responsiblePersistenceService.requestResponsiblePage(event);
	}

	public ResponsibleDetailsEvent requestResponsibleDetails(RequestResponsibleDetailsEvent event ) throws Exception{
		return responsiblePersistenceService.requestResponsibleDetails(event);
	}

	public ResponsibleDetailsEvent modifyResponsible(ResponsibleModificationEvent event) throws Exception {
		return responsiblePersistenceService.modifyResponsible(event);
	}
	
	public ResponsibleDetailsEvent requestOnlyRecord() throws Exception {
		return responsiblePersistenceService.getOnlyRecord();
	}
	
	public ResponsiblePageEvent requestResponsibleFilterPage(RequestResponsiblePageEvent event) throws Exception {
		return responsiblePersistenceService.requestResponsibleFilterPage(event);
	}
	
	public ResponsiblePageEvent requestResponsibleFilter(RequestResponsiblePageEvent event) throws Exception {
		return responsiblePersistenceService.requestResponsibleFilter(event);
	}
	

	public ResponsiblePageEvent requestAllByJob(EmbeddedField... fields) throws Exception {
		return responsiblePersistenceService.requestAllByJob(fields);
	}public ResponsiblePageEvent requestAllByThird(EmbeddedField... fields) throws Exception {
		return responsiblePersistenceService.requestAllByThird(fields);
	}
	
	
	
}