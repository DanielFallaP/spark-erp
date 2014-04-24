package co.com.cybersoft.persistence.repository.contract;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.Contract;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface ContractRepository extends MongoRepository<Contract, String>{
	Contract findByCode(String code);
	Contract findByDescription(String description);
	
}