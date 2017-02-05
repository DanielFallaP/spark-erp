package co.com.cybersoft.maintenance.tables.persistence.services.failureCauseTechnicalaction;

import co.com.cybersoft.maintenance.tables.persistence.repository.failureCauseTechnicalaction.FailureCauseTechnicalactionRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class FailureCauseTechnicalactionPersistenceFactory {

	FailureCauseTechnicalactionRepository failureCauseTechnicalactionRepository;
	
	public FailureCauseTechnicalactionPersistenceFactory(FailureCauseTechnicalactionRepository failureCauseTechnicalactionRepository){
		this.failureCauseTechnicalactionRepository=failureCauseTechnicalactionRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.FailureCauseTechnicalaction getFailureCauseTechnicalactionByField(String field, String value){
		
		return null;
	}
}