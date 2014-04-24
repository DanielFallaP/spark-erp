package co.com.cybersoft.persistence.repository.afe;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.Afe;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface AfeRepository extends MongoRepository<Afe, String>{
	Afe findByCode(String code);
	Afe findByDescription(String description);
	
}