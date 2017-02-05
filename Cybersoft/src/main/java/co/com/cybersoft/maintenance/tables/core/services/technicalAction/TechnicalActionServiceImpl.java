package co.com.cybersoft.maintenance.tables.core.services.technicalAction;

import co.com.cybersoft.maintenance.tables.events.technicalAction.CreateTechnicalActionEvent;
import co.com.cybersoft.maintenance.tables.events.technicalAction.TechnicalActionDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.technicalAction.TechnicalActionPageEvent;
import co.com.cybersoft.maintenance.tables.events.technicalAction.TechnicalActionModificationEvent;
import co.com.cybersoft.maintenance.tables.events.technicalAction.RequestTechnicalActionDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.technicalAction.RequestTechnicalActionPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.technicalAction.TechnicalActionPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class TechnicalActionServiceImpl implements TechnicalActionService{

	private final TechnicalActionPersistenceService technicalActionPersistenceService;
	
	public TechnicalActionServiceImpl(final TechnicalActionPersistenceService technicalActionPersistenceService){
		this.technicalActionPersistenceService=technicalActionPersistenceService;
	}
	
	/**
	 */
	public TechnicalActionDetailsEvent createTechnicalAction(CreateTechnicalActionEvent event ) throws Exception{
		return technicalActionPersistenceService.createTechnicalAction(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public TechnicalActionPageEvent requestTechnicalActionPage(RequestTechnicalActionPageEvent event) throws Exception{
		return technicalActionPersistenceService.requestTechnicalActionPage(event);
	}

	public TechnicalActionDetailsEvent requestTechnicalActionDetails(RequestTechnicalActionDetailsEvent event ) throws Exception{
		return technicalActionPersistenceService.requestTechnicalActionDetails(event);
	}

	public TechnicalActionDetailsEvent modifyTechnicalAction(TechnicalActionModificationEvent event) throws Exception {
		return technicalActionPersistenceService.modifyTechnicalAction(event);
	}
	
	public TechnicalActionDetailsEvent requestOnlyRecord() throws Exception {
		return technicalActionPersistenceService.getOnlyRecord();
	}
	
	public TechnicalActionPageEvent requestTechnicalActionFilterPage(RequestTechnicalActionPageEvent event) throws Exception {
		return technicalActionPersistenceService.requestTechnicalActionFilterPage(event);
	}
	
	public TechnicalActionPageEvent requestTechnicalActionFilter(RequestTechnicalActionPageEvent event) throws Exception {
		return technicalActionPersistenceService.requestTechnicalActionFilter(event);
	}
	

	public TechnicalActionPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return technicalActionPersistenceService.requestAllByCompany(fields);
	}public TechnicalActionPageEvent requestAllByCode(EmbeddedField... fields) throws Exception {
		return technicalActionPersistenceService.requestAllByCode(fields);
	}public TechnicalActionPageEvent requestAllByTechnicalActionName(EmbeddedField... fields) throws Exception {
		return technicalActionPersistenceService.requestAllByTechnicalActionName(fields);
	}
	
	
	
}