package co.com.cybersoft.maintenance.tables.persistence.services.jobThird;

import co.com.cybersoft.maintenance.tables.persistence.repository.jobThird.JobThirdRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class JobThirdPersistenceFactory {

	JobThirdRepository jobThirdRepository;
	
	public JobThirdPersistenceFactory(JobThirdRepository jobThirdRepository){
		this.jobThirdRepository=jobThirdRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.JobThird getJobThirdByField(String field, String value){
		
		return null;
	}
}