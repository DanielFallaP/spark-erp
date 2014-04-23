package co.com.cybersoft.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.Afe;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface AfeRepository extends MongoRepository<Afe, String>{
	Afe findByCode(String code);
}