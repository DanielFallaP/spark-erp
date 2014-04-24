package co.com.cybersoft.persistence.repository.corporation;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.Corporation;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface CorporationRepository extends MongoRepository<Corporation, String>{
	Corporation findByCode(Integer code);
	Corporation findByDescription(String description);
	
}