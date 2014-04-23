package co.com.cybersoft.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.CalculusType;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface CalculusTypeRepository extends MongoRepository<CalculusType, String>{
	CalculusType findByCode(String code);
}