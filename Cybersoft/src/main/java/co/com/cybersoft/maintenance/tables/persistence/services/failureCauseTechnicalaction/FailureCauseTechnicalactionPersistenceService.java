package co.com.cybersoft.maintenance.tables.persistence.services.failureCauseTechnicalaction;

import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.CreateFailureCauseTechnicalactionEvent;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.FailureCauseTechnicalactionDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.FailureCauseTechnicalactionPageEvent;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.FailureCauseTechnicalactionModificationEvent;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.RequestFailureCauseTechnicalactionDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.RequestFailureCauseTechnicalactionPageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface FailureCauseTechnicalactionPersistenceService {

	FailureCauseTechnicalactionDetailsEvent createFailureCauseTechnicalaction(CreateFailureCauseTechnicalactionEvent event) throws Exception;

	FailureCauseTechnicalactionPageEvent requestFailureCauseTechnicalactionPage(RequestFailureCauseTechnicalactionPageEvent event) throws Exception;

	FailureCauseTechnicalactionDetailsEvent requestFailureCauseTechnicalactionDetails(RequestFailureCauseTechnicalactionDetailsEvent event) throws Exception;
	
	FailureCauseTechnicalactionDetailsEvent modifyFailureCauseTechnicalaction(FailureCauseTechnicalactionModificationEvent event) throws Exception;
	FailureCauseTechnicalactionPageEvent requestFailureCauseTechnicalactionFilterPage(RequestFailureCauseTechnicalactionPageEvent event) throws Exception;
	FailureCauseTechnicalactionPageEvent requestFailureCauseTechnicalactionFilter(RequestFailureCauseTechnicalactionPageEvent event) throws Exception;
	
	FailureCauseTechnicalactionPageEvent requestAllByFailureCause(EmbeddedField... fields) throws Exception;
	FailureCauseTechnicalactionPageEvent requestAllByTechnicalAction(EmbeddedField... fields) throws Exception;

	
	
	FailureCauseTechnicalactionDetailsEvent getOnlyRecord() throws Exception;
	
}