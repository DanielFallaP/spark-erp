package co.com.cybersoft.persistence.repository.active;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.Active;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface ActiveRepository extends MongoRepository<Active, String>{
	Active findByCode(String code);
	
}