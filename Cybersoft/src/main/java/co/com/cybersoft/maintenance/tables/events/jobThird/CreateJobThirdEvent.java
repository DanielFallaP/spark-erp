package co.com.cybersoft.maintenance.tables.events.jobThird;

import co.com.cybersoft.maintenance.tables.core.domain.JobThirdDetails;

/**
 * Event class for JobThird
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateJobThirdEvent {
		
	private JobThirdDetails jobThirdDetails;
	
	public CreateJobThirdEvent(JobThirdDetails jobThirdDetails){
		this.jobThirdDetails=jobThirdDetails;
	}

	public JobThirdDetails getJobThirdDetails() {
		return jobThirdDetails;
	}
	
	
}