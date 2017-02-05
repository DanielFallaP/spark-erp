package co.com.cybersoft.maintenance.tables.events.job;

import co.com.cybersoft.maintenance.tables.core.domain.JobDetails;

/**
 * Event class for Job
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateJobEvent {
		
	private JobDetails jobDetails;
	
	public CreateJobEvent(JobDetails jobDetails){
		this.jobDetails=jobDetails;
	}

	public JobDetails getJobDetails() {
		return jobDetails;
	}
	
	
}