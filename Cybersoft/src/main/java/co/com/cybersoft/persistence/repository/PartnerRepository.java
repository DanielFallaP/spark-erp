package co.com.cybersoft.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.Partner;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface PartnerRepository extends MongoRepository<Partner, String>{
	Partner findByCode(String code);
}