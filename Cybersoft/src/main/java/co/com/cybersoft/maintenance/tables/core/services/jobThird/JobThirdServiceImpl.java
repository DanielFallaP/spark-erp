package co.com.cybersoft.maintenance.tables.core.services.jobThird;

import co.com.cybersoft.maintenance.tables.events.jobThird.CreateJobThirdEvent;
import co.com.cybersoft.maintenance.tables.events.jobThird.JobThirdDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.jobThird.JobThirdPageEvent;
import co.com.cybersoft.maintenance.tables.events.jobThird.JobThirdModificationEvent;
import co.com.cybersoft.maintenance.tables.events.jobThird.RequestJobThirdDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.jobThird.RequestJobThirdPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.jobThird.JobThirdPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class JobThirdServiceImpl implements JobThirdService{

	private final JobThirdPersistenceService jobThirdPersistenceService;
	
	public JobThirdServiceImpl(final JobThirdPersistenceService jobThirdPersistenceService){
		this.jobThirdPersistenceService=jobThirdPersistenceService;
	}
	
	/**
	 */
	public JobThirdDetailsEvent createJobThird(CreateJobThirdEvent event ) throws Exception{
		return jobThirdPersistenceService.createJobThird(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public JobThirdPageEvent requestJobThirdPage(RequestJobThirdPageEvent event) throws Exception{
		return jobThirdPersistenceService.requestJobThirdPage(event);
	}

	public JobThirdDetailsEvent requestJobThirdDetails(RequestJobThirdDetailsEvent event ) throws Exception{
		return jobThirdPersistenceService.requestJobThirdDetails(event);
	}

	public JobThirdDetailsEvent modifyJobThird(JobThirdModificationEvent event) throws Exception {
		return jobThirdPersistenceService.modifyJobThird(event);
	}
	
	public JobThirdDetailsEvent requestOnlyRecord() throws Exception {
		return jobThirdPersistenceService.getOnlyRecord();
	}
	
	public JobThirdPageEvent requestJobThirdFilterPage(RequestJobThirdPageEvent event) throws Exception {
		return jobThirdPersistenceService.requestJobThirdFilterPage(event);
	}
	
	public JobThirdPageEvent requestJobThirdFilter(RequestJobThirdPageEvent event) throws Exception {
		return jobThirdPersistenceService.requestJobThirdFilter(event);
	}
	

	public JobThirdPageEvent requestAllByJob(EmbeddedField... fields) throws Exception {
		return jobThirdPersistenceService.requestAllByJob(fields);
	}public JobThirdPageEvent requestAllByThird(EmbeddedField... fields) throws Exception {
		return jobThirdPersistenceService.requestAllByThird(fields);
	}
	
	
	
}