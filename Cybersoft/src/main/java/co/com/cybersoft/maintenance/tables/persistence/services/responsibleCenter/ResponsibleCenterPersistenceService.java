package co.com.cybersoft.maintenance.tables.persistence.services.responsibleCenter;

import co.com.cybersoft.maintenance.tables.events.responsibleCenter.CreateResponsibleCenterEvent;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.ResponsibleCenterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.ResponsibleCenterPageEvent;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.ResponsibleCenterModificationEvent;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.RequestResponsibleCenterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.RequestResponsibleCenterPageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface ResponsibleCenterPersistenceService {

	ResponsibleCenterDetailsEvent createResponsibleCenter(CreateResponsibleCenterEvent event) throws Exception;

	ResponsibleCenterPageEvent requestResponsibleCenterPage(RequestResponsibleCenterPageEvent event) throws Exception;

	ResponsibleCenterDetailsEvent requestResponsibleCenterDetails(RequestResponsibleCenterDetailsEvent event) throws Exception;
	
	ResponsibleCenterDetailsEvent modifyResponsibleCenter(ResponsibleCenterModificationEvent event) throws Exception;
	ResponsibleCenterPageEvent requestResponsibleCenterFilterPage(RequestResponsibleCenterPageEvent event) throws Exception;
	ResponsibleCenterPageEvent requestResponsibleCenterFilter(RequestResponsibleCenterPageEvent event) throws Exception;
	
	ResponsibleCenterPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	ResponsibleCenterPageEvent requestAllByNameResponsibleCenter(EmbeddedField... fields) throws Exception;

	
	
	ResponsibleCenterDetailsEvent getOnlyRecord() throws Exception;
	
}