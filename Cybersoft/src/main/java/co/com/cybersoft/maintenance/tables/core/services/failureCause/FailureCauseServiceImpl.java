package co.com.cybersoft.maintenance.tables.core.services.failureCause;

import co.com.cybersoft.maintenance.tables.events.failureCause.CreateFailureCauseEvent;
import co.com.cybersoft.maintenance.tables.events.failureCause.FailureCauseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.failureCause.FailureCausePageEvent;
import co.com.cybersoft.maintenance.tables.events.failureCause.FailureCauseModificationEvent;
import co.com.cybersoft.maintenance.tables.events.failureCause.RequestFailureCauseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.failureCause.RequestFailureCausePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.failureCause.FailureCausePersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class FailureCauseServiceImpl implements FailureCauseService{

	private final FailureCausePersistenceService failureCausePersistenceService;
	
	public FailureCauseServiceImpl(final FailureCausePersistenceService failureCausePersistenceService){
		this.failureCausePersistenceService=failureCausePersistenceService;
	}
	
	/**
	 */
	public FailureCauseDetailsEvent createFailureCause(CreateFailureCauseEvent event ) throws Exception{
		return failureCausePersistenceService.createFailureCause(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public FailureCausePageEvent requestFailureCausePage(RequestFailureCausePageEvent event) throws Exception{
		return failureCausePersistenceService.requestFailureCausePage(event);
	}

	public FailureCauseDetailsEvent requestFailureCauseDetails(RequestFailureCauseDetailsEvent event ) throws Exception{
		return failureCausePersistenceService.requestFailureCauseDetails(event);
	}

	public FailureCauseDetailsEvent modifyFailureCause(FailureCauseModificationEvent event) throws Exception {
		return failureCausePersistenceService.modifyFailureCause(event);
	}
	
	public FailureCauseDetailsEvent requestOnlyRecord() throws Exception {
		return failureCausePersistenceService.getOnlyRecord();
	}
	
	public FailureCausePageEvent requestFailureCauseFilterPage(RequestFailureCausePageEvent event) throws Exception {
		return failureCausePersistenceService.requestFailureCauseFilterPage(event);
	}
	
	public FailureCausePageEvent requestFailureCauseFilter(RequestFailureCausePageEvent event) throws Exception {
		return failureCausePersistenceService.requestFailureCauseFilter(event);
	}
	

	public FailureCausePageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return failureCausePersistenceService.requestAllByCompany(fields);
	}public FailureCausePageEvent requestAllByNameFailureCause(EmbeddedField... fields) throws Exception {
		return failureCausePersistenceService.requestAllByNameFailureCause(fields);
	}
	
	
	
}