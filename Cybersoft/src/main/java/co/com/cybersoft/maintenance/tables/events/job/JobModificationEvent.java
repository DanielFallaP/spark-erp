package co.com.cybersoft.maintenance.tables.events.job;

import co.com.cybersoft.maintenance.tables.core.domain.JobDetails;

/**
 * Event class for Job
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class JobModificationEvent {

	private JobDetails jobDetails;
	
	public JobModificationEvent(JobDetails jobDetails){
		this.jobDetails=jobDetails;
	}

	public JobDetails getJobDetails() {
		return jobDetails;
	}
	
}