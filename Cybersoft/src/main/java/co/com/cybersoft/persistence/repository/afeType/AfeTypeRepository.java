package co.com.cybersoft.persistence.repository.afeType;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.AfeType;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface AfeTypeRepository extends MongoRepository<AfeType, String>{
	AfeType findByCode(String code);
	AfeType findByDescription(String description);
	
}