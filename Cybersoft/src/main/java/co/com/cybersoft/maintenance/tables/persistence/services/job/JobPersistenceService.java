package co.com.cybersoft.maintenance.tables.persistence.services.job;

import co.com.cybersoft.maintenance.tables.events.job.CreateJobEvent;
import co.com.cybersoft.maintenance.tables.events.job.JobDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.job.JobPageEvent;
import co.com.cybersoft.maintenance.tables.events.job.JobModificationEvent;
import co.com.cybersoft.maintenance.tables.events.job.RequestJobDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.job.RequestJobPageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface JobPersistenceService {

	JobDetailsEvent createJob(CreateJobEvent event) throws Exception;

	JobPageEvent requestJobPage(RequestJobPageEvent event) throws Exception;

	JobDetailsEvent requestJobDetails(RequestJobDetailsEvent event) throws Exception;
	
	JobDetailsEvent modifyJob(JobModificationEvent event) throws Exception;
	JobPageEvent requestJobFilterPage(RequestJobPageEvent event) throws Exception;
	JobPageEvent requestJobFilter(RequestJobPageEvent event) throws Exception;
	
	JobPageEvent requestAllByResponsibleCenter(EmbeddedField... fields) throws Exception;
	JobPageEvent requestAllByNameJob(EmbeddedField... fields) throws Exception;
	JobPageEvent requestAllByTypeWork(EmbeddedField... fields) throws Exception;

	
	
	JobDetailsEvent getOnlyRecord() throws Exception;
	
}