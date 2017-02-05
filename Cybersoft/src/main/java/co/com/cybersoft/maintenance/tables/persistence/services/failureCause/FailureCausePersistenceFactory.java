package co.com.cybersoft.maintenance.tables.persistence.services.failureCause;

import co.com.cybersoft.maintenance.tables.persistence.repository.failureCause.FailureCauseRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class FailureCausePersistenceFactory {

	FailureCauseRepository failureCauseRepository;
	
	public FailureCausePersistenceFactory(FailureCauseRepository failureCauseRepository){
		this.failureCauseRepository=failureCauseRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.FailureCause getFailureCauseByField(String field, String value){
		if (field.equals("nameFailureCause"))
					return failureCauseRepository.findByNameFailureCause(value);		
		return null;
	}
}