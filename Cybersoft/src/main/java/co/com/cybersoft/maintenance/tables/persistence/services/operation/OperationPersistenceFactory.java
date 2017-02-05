package co.com.cybersoft.maintenance.tables.persistence.services.operation;

import co.com.cybersoft.maintenance.tables.persistence.repository.operation.OperationRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class OperationPersistenceFactory {

	OperationRepository operationRepository;
	
	public OperationPersistenceFactory(OperationRepository operationRepository){
		this.operationRepository=operationRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.Operation getOperationByField(String field, String value){
		if (field.equals("codeOperation"))
					return operationRepository.findByCodeOperation(value);if (field.equals("nameOperation"))
					return operationRepository.findByNameOperation(value);		
		return null;
	}
}