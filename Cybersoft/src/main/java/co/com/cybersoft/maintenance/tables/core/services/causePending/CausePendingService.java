package co.com.cybersoft.maintenance.tables.core.services.causePending;

import co.com.cybersoft.maintenance.tables.events.causePending.CreateCausePendingEvent;
import co.com.cybersoft.maintenance.tables.events.causePending.CausePendingDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.causePending.CausePendingPageEvent;
import co.com.cybersoft.maintenance.tables.events.causePending.CausePendingModificationEvent;
import co.com.cybersoft.maintenance.tables.events.causePending.RequestCausePendingDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.causePending.RequestCausePendingPageEvent;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface CausePendingService {
	CausePendingDetailsEvent requestOnlyRecord() throws Exception;

	CausePendingDetailsEvent createCausePending(CreateCausePendingEvent event ) throws Exception;
	
	CausePendingPageEvent requestCausePendingPage(RequestCausePendingPageEvent event) throws Exception;

	CausePendingDetailsEvent requestCausePendingDetails(RequestCausePendingDetailsEvent event ) throws Exception;

	CausePendingDetailsEvent modifyCausePending(CausePendingModificationEvent event) throws Exception;
	
	CausePendingPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	CausePendingPageEvent requestAllByNameCausePending(EmbeddedField... fields) throws Exception;

	
	
	CausePendingPageEvent requestCausePendingFilterPage(RequestCausePendingPageEvent event) throws Exception;

	CausePendingPageEvent requestCausePendingFilter(RequestCausePendingPageEvent event) throws Exception;
	
}