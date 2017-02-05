package co.com.cybersoft.maintenance.tables.persistence.services.causeClose;

import co.com.cybersoft.maintenance.tables.events.causeClose.CreateCauseCloseEvent;
import co.com.cybersoft.maintenance.tables.events.causeClose.CauseCloseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.causeClose.CauseClosePageEvent;
import co.com.cybersoft.maintenance.tables.events.causeClose.CauseCloseModificationEvent;
import co.com.cybersoft.maintenance.tables.events.causeClose.RequestCauseCloseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.causeClose.RequestCauseClosePageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface CauseClosePersistenceService {

	CauseCloseDetailsEvent createCauseClose(CreateCauseCloseEvent event) throws Exception;

	CauseClosePageEvent requestCauseClosePage(RequestCauseClosePageEvent event) throws Exception;

	CauseCloseDetailsEvent requestCauseCloseDetails(RequestCauseCloseDetailsEvent event) throws Exception;
	
	CauseCloseDetailsEvent modifyCauseClose(CauseCloseModificationEvent event) throws Exception;
	CauseClosePageEvent requestCauseCloseFilterPage(RequestCauseClosePageEvent event) throws Exception;
	CauseClosePageEvent requestCauseCloseFilter(RequestCauseClosePageEvent event) throws Exception;
	
	CauseClosePageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	CauseClosePageEvent requestAllByNameCauseClose(EmbeddedField... fields) throws Exception;
	CauseClosePageEvent requestAllByTypeCauseClose(EmbeddedField... fields) throws Exception;

	
	
	CauseCloseDetailsEvent getOnlyRecord() throws Exception;
	
}