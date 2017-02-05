package co.com.cybersoft.maintenance.tables.core.services.failureCauseTechnicalaction;

import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.CreateFailureCauseTechnicalactionEvent;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.FailureCauseTechnicalactionDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.FailureCauseTechnicalactionPageEvent;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.FailureCauseTechnicalactionModificationEvent;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.RequestFailureCauseTechnicalactionDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.RequestFailureCauseTechnicalactionPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.failureCauseTechnicalaction.FailureCauseTechnicalactionPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class FailureCauseTechnicalactionServiceImpl implements FailureCauseTechnicalactionService{

	private final FailureCauseTechnicalactionPersistenceService failureCauseTechnicalactionPersistenceService;
	
	public FailureCauseTechnicalactionServiceImpl(final FailureCauseTechnicalactionPersistenceService failureCauseTechnicalactionPersistenceService){
		this.failureCauseTechnicalactionPersistenceService=failureCauseTechnicalactionPersistenceService;
	}
	
	/**
	 */
	public FailureCauseTechnicalactionDetailsEvent createFailureCauseTechnicalaction(CreateFailureCauseTechnicalactionEvent event ) throws Exception{
		return failureCauseTechnicalactionPersistenceService.createFailureCauseTechnicalaction(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public FailureCauseTechnicalactionPageEvent requestFailureCauseTechnicalactionPage(RequestFailureCauseTechnicalactionPageEvent event) throws Exception{
		return failureCauseTechnicalactionPersistenceService.requestFailureCauseTechnicalactionPage(event);
	}

	public FailureCauseTechnicalactionDetailsEvent requestFailureCauseTechnicalactionDetails(RequestFailureCauseTechnicalactionDetailsEvent event ) throws Exception{
		return failureCauseTechnicalactionPersistenceService.requestFailureCauseTechnicalactionDetails(event);
	}

	public FailureCauseTechnicalactionDetailsEvent modifyFailureCauseTechnicalaction(FailureCauseTechnicalactionModificationEvent event) throws Exception {
		return failureCauseTechnicalactionPersistenceService.modifyFailureCauseTechnicalaction(event);
	}
	
	public FailureCauseTechnicalactionDetailsEvent requestOnlyRecord() throws Exception {
		return failureCauseTechnicalactionPersistenceService.getOnlyRecord();
	}
	
	public FailureCauseTechnicalactionPageEvent requestFailureCauseTechnicalactionFilterPage(RequestFailureCauseTechnicalactionPageEvent event) throws Exception {
		return failureCauseTechnicalactionPersistenceService.requestFailureCauseTechnicalactionFilterPage(event);
	}
	
	public FailureCauseTechnicalactionPageEvent requestFailureCauseTechnicalactionFilter(RequestFailureCauseTechnicalactionPageEvent event) throws Exception {
		return failureCauseTechnicalactionPersistenceService.requestFailureCauseTechnicalactionFilter(event);
	}
	

	public FailureCauseTechnicalactionPageEvent requestAllByFailureCause(EmbeddedField... fields) throws Exception {
		return failureCauseTechnicalactionPersistenceService.requestAllByFailureCause(fields);
	}public FailureCauseTechnicalactionPageEvent requestAllByTechnicalAction(EmbeddedField... fields) throws Exception {
		return failureCauseTechnicalactionPersistenceService.requestAllByTechnicalAction(fields);
	}
	
	
	
}