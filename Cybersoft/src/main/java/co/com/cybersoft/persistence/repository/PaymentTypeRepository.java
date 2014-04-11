package co.com.cybersoft.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.PaymentType;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface PaymentTypeRepository extends MongoRepository<PaymentType, String>{
	PaymentType findByCode(String code);
}