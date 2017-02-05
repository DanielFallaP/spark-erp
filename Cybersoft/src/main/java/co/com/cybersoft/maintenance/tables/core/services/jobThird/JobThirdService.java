package co.com.cybersoft.maintenance.tables.core.services.jobThird;

import co.com.cybersoft.maintenance.tables.events.jobThird.CreateJobThirdEvent;
import co.com.cybersoft.maintenance.tables.events.jobThird.JobThirdDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.jobThird.JobThirdPageEvent;
import co.com.cybersoft.maintenance.tables.events.jobThird.JobThirdModificationEvent;
import co.com.cybersoft.maintenance.tables.events.jobThird.RequestJobThirdDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.jobThird.RequestJobThirdPageEvent;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface JobThirdService {
	JobThirdDetailsEvent requestOnlyRecord() throws Exception;

	JobThirdDetailsEvent createJobThird(CreateJobThirdEvent event ) throws Exception;
	
	JobThirdPageEvent requestJobThirdPage(RequestJobThirdPageEvent event) throws Exception;

	JobThirdDetailsEvent requestJobThirdDetails(RequestJobThirdDetailsEvent event ) throws Exception;

	JobThirdDetailsEvent modifyJobThird(JobThirdModificationEvent event) throws Exception;
	
	JobThirdPageEvent requestAllByJob(EmbeddedField... fields) throws Exception;
	JobThirdPageEvent requestAllByThird(EmbeddedField... fields) throws Exception;

	
	
	JobThirdPageEvent requestJobThirdFilterPage(RequestJobThirdPageEvent event) throws Exception;

	JobThirdPageEvent requestJobThirdFilter(RequestJobThirdPageEvent event) throws Exception;
	
}