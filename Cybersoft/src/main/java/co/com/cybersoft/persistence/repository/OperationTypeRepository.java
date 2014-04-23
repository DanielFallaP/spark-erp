package co.com.cybersoft.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.OperationType;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface OperationTypeRepository extends MongoRepository<OperationType, String>{
	OperationType findByCode(String code);
}