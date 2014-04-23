package co.com.cybersoft.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.JointVenture;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface JointVentureRepository extends MongoRepository<JointVenture, String>{
	JointVenture findByCode(String code);
}