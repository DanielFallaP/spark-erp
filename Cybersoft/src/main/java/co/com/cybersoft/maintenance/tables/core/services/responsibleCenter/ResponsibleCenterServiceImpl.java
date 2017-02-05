package co.com.cybersoft.maintenance.tables.core.services.responsibleCenter;

import co.com.cybersoft.maintenance.tables.events.responsibleCenter.CreateResponsibleCenterEvent;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.ResponsibleCenterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.ResponsibleCenterPageEvent;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.ResponsibleCenterModificationEvent;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.RequestResponsibleCenterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.RequestResponsibleCenterPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.responsibleCenter.ResponsibleCenterPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ResponsibleCenterServiceImpl implements ResponsibleCenterService{

	private final ResponsibleCenterPersistenceService responsibleCenterPersistenceService;
	
	public ResponsibleCenterServiceImpl(final ResponsibleCenterPersistenceService responsibleCenterPersistenceService){
		this.responsibleCenterPersistenceService=responsibleCenterPersistenceService;
	}
	
	/**
	 */
	public ResponsibleCenterDetailsEvent createResponsibleCenter(CreateResponsibleCenterEvent event ) throws Exception{
		return responsibleCenterPersistenceService.createResponsibleCenter(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public ResponsibleCenterPageEvent requestResponsibleCenterPage(RequestResponsibleCenterPageEvent event) throws Exception{
		return responsibleCenterPersistenceService.requestResponsibleCenterPage(event);
	}

	public ResponsibleCenterDetailsEvent requestResponsibleCenterDetails(RequestResponsibleCenterDetailsEvent event ) throws Exception{
		return responsibleCenterPersistenceService.requestResponsibleCenterDetails(event);
	}

	public ResponsibleCenterDetailsEvent modifyResponsibleCenter(ResponsibleCenterModificationEvent event) throws Exception {
		return responsibleCenterPersistenceService.modifyResponsibleCenter(event);
	}
	
	public ResponsibleCenterDetailsEvent requestOnlyRecord() throws Exception {
		return responsibleCenterPersistenceService.getOnlyRecord();
	}
	
	public ResponsibleCenterPageEvent requestResponsibleCenterFilterPage(RequestResponsibleCenterPageEvent event) throws Exception {
		return responsibleCenterPersistenceService.requestResponsibleCenterFilterPage(event);
	}
	
	public ResponsibleCenterPageEvent requestResponsibleCenterFilter(RequestResponsibleCenterPageEvent event) throws Exception {
		return responsibleCenterPersistenceService.requestResponsibleCenterFilter(event);
	}
	

	public ResponsibleCenterPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return responsibleCenterPersistenceService.requestAllByCompany(fields);
	}public ResponsibleCenterPageEvent requestAllByNameResponsibleCenter(EmbeddedField... fields) throws Exception {
		return responsibleCenterPersistenceService.requestAllByNameResponsibleCenter(fields);
	}
	
	
	
}