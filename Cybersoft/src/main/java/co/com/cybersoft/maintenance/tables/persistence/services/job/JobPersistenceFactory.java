package co.com.cybersoft.maintenance.tables.persistence.services.job;

import co.com.cybersoft.maintenance.tables.persistence.repository.job.JobRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class JobPersistenceFactory {

	JobRepository jobRepository;
	
	public JobPersistenceFactory(JobRepository jobRepository){
		this.jobRepository=jobRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.Job getJobByField(String field, String value){
		if (field.equals("nameJob"))
					return jobRepository.findByNameJob(value);		
		return null;
	}
}