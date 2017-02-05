package co.com.cybersoft.maintenance.tables.core.services.failureCause;

import co.com.cybersoft.maintenance.tables.events.failureCause.CreateFailureCauseEvent;
import co.com.cybersoft.maintenance.tables.events.failureCause.FailureCauseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.failureCause.FailureCausePageEvent;
import co.com.cybersoft.maintenance.tables.events.failureCause.FailureCauseModificationEvent;
import co.com.cybersoft.maintenance.tables.events.failureCause.RequestFailureCauseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.failureCause.RequestFailureCausePageEvent;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface FailureCauseService {
	FailureCauseDetailsEvent requestOnlyRecord() throws Exception;

	FailureCauseDetailsEvent createFailureCause(CreateFailureCauseEvent event ) throws Exception;
	
	FailureCausePageEvent requestFailureCausePage(RequestFailureCausePageEvent event) throws Exception;

	FailureCauseDetailsEvent requestFailureCauseDetails(RequestFailureCauseDetailsEvent event ) throws Exception;

	FailureCauseDetailsEvent modifyFailureCause(FailureCauseModificationEvent event) throws Exception;
	
	FailureCausePageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	FailureCausePageEvent requestAllByNameFailureCause(EmbeddedField... fields) throws Exception;

	
	
	FailureCausePageEvent requestFailureCauseFilterPage(RequestFailureCausePageEvent event) throws Exception;

	FailureCausePageEvent requestFailureCauseFilter(RequestFailureCausePageEvent event) throws Exception;
	
}