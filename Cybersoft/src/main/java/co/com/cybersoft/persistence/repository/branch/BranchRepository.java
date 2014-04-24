package co.com.cybersoft.persistence.repository.branch;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.Branch;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface BranchRepository extends MongoRepository<Branch, String>{
	Branch findByCode(Integer code);
	Branch findByDescription(String description);
	
}