package co.com.cybersoft.maintenance.tables.persistence.services.referenceOperation;

import co.com.cybersoft.maintenance.tables.persistence.repository.referenceOperation.ReferenceOperationRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ReferenceOperationPersistenceFactory {

	ReferenceOperationRepository referenceOperationRepository;
	
	public ReferenceOperationPersistenceFactory(ReferenceOperationRepository referenceOperationRepository){
		this.referenceOperationRepository=referenceOperationRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.ReferenceOperation getReferenceOperationByField(String field, String value){
		
		return null;
	}
}