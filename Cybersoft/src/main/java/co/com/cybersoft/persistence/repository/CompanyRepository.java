package co.com.cybersoft.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.Company;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface CompanyRepository extends MongoRepository<Company, String>{
	Company findByCode(String code);
}