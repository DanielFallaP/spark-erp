package co.com.cybersoft.maintenance.tables.persistence.services.responsible;

import co.com.cybersoft.maintenance.tables.events.responsible.CreateResponsibleEvent;
import co.com.cybersoft.maintenance.tables.events.responsible.ResponsibleDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.responsible.ResponsiblePageEvent;
import co.com.cybersoft.maintenance.tables.events.responsible.ResponsibleModificationEvent;
import co.com.cybersoft.maintenance.tables.events.responsible.RequestResponsibleDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.responsible.RequestResponsiblePageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface ResponsiblePersistenceService {

	ResponsibleDetailsEvent createResponsible(CreateResponsibleEvent event) throws Exception;

	ResponsiblePageEvent requestResponsiblePage(RequestResponsiblePageEvent event) throws Exception;

	ResponsibleDetailsEvent requestResponsibleDetails(RequestResponsibleDetailsEvent event) throws Exception;
	
	ResponsibleDetailsEvent modifyResponsible(ResponsibleModificationEvent event) throws Exception;
	ResponsiblePageEvent requestResponsibleFilterPage(RequestResponsiblePageEvent event) throws Exception;
	ResponsiblePageEvent requestResponsibleFilter(RequestResponsiblePageEvent event) throws Exception;
	
	ResponsiblePageEvent requestAllByJob(EmbeddedField... fields) throws Exception;
	ResponsiblePageEvent requestAllByThird(EmbeddedField... fields) throws Exception;

	
	
	ResponsibleDetailsEvent getOnlyRecord() throws Exception;
	
}