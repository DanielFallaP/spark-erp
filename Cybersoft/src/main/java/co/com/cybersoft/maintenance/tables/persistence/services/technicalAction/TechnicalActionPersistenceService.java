package co.com.cybersoft.maintenance.tables.persistence.services.technicalAction;

import co.com.cybersoft.maintenance.tables.events.technicalAction.CreateTechnicalActionEvent;
import co.com.cybersoft.maintenance.tables.events.technicalAction.TechnicalActionDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.technicalAction.TechnicalActionPageEvent;
import co.com.cybersoft.maintenance.tables.events.technicalAction.TechnicalActionModificationEvent;
import co.com.cybersoft.maintenance.tables.events.technicalAction.RequestTechnicalActionDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.technicalAction.RequestTechnicalActionPageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface TechnicalActionPersistenceService {

	TechnicalActionDetailsEvent createTechnicalAction(CreateTechnicalActionEvent event) throws Exception;

	TechnicalActionPageEvent requestTechnicalActionPage(RequestTechnicalActionPageEvent event) throws Exception;

	TechnicalActionDetailsEvent requestTechnicalActionDetails(RequestTechnicalActionDetailsEvent event) throws Exception;
	
	TechnicalActionDetailsEvent modifyTechnicalAction(TechnicalActionModificationEvent event) throws Exception;
	TechnicalActionPageEvent requestTechnicalActionFilterPage(RequestTechnicalActionPageEvent event) throws Exception;
	TechnicalActionPageEvent requestTechnicalActionFilter(RequestTechnicalActionPageEvent event) throws Exception;
	
	TechnicalActionPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	TechnicalActionPageEvent requestAllByCode(EmbeddedField... fields) throws Exception;
	TechnicalActionPageEvent requestAllByTechnicalActionName(EmbeddedField... fields) throws Exception;

	
	
	TechnicalActionDetailsEvent getOnlyRecord() throws Exception;
	
}