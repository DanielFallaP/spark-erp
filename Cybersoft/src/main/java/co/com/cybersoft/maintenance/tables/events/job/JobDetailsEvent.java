package co.com.cybersoft.maintenance.tables.events.job;

import co.com.cybersoft.maintenance.tables.core.domain.JobDetails;

/**
 * Event class for Job
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class JobDetailsEvent {
	
	private JobDetails jobDetails;
	
	public JobDetailsEvent(JobDetails jobDetails){
		this.jobDetails=jobDetails;
	}

	public JobDetails getJobDetails() {
		return jobDetails;
	}

}