package co.com.cybersoft.maintenance.tables.core.services.job;

import co.com.cybersoft.maintenance.tables.events.job.CreateJobEvent;
import co.com.cybersoft.maintenance.tables.events.job.JobDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.job.JobPageEvent;
import co.com.cybersoft.maintenance.tables.events.job.JobModificationEvent;
import co.com.cybersoft.maintenance.tables.events.job.RequestJobDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.job.RequestJobPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.job.JobPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class JobServiceImpl implements JobService{

	private final JobPersistenceService jobPersistenceService;
	
	public JobServiceImpl(final JobPersistenceService jobPersistenceService){
		this.jobPersistenceService=jobPersistenceService;
	}
	
	/**
	 */
	public JobDetailsEvent createJob(CreateJobEvent event ) throws Exception{
		return jobPersistenceService.createJob(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public JobPageEvent requestJobPage(RequestJobPageEvent event) throws Exception{
		return jobPersistenceService.requestJobPage(event);
	}

	public JobDetailsEvent requestJobDetails(RequestJobDetailsEvent event ) throws Exception{
		return jobPersistenceService.requestJobDetails(event);
	}

	public JobDetailsEvent modifyJob(JobModificationEvent event) throws Exception {
		return jobPersistenceService.modifyJob(event);
	}
	
	public JobDetailsEvent requestOnlyRecord() throws Exception {
		return jobPersistenceService.getOnlyRecord();
	}
	
	public JobPageEvent requestJobFilterPage(RequestJobPageEvent event) throws Exception {
		return jobPersistenceService.requestJobFilterPage(event);
	}
	
	public JobPageEvent requestJobFilter(RequestJobPageEvent event) throws Exception {
		return jobPersistenceService.requestJobFilter(event);
	}
	

	public JobPageEvent requestAllByResponsibleCenter(EmbeddedField... fields) throws Exception {
		return jobPersistenceService.requestAllByResponsibleCenter(fields);
	}public JobPageEvent requestAllByNameJob(EmbeddedField... fields) throws Exception {
		return jobPersistenceService.requestAllByNameJob(fields);
	}public JobPageEvent requestAllByTypeWork(EmbeddedField... fields) throws Exception {
		return jobPersistenceService.requestAllByTypeWork(fields);
	}
	
	
	
}