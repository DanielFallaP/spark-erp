package co.com.cybersoft.persistence.repository.bill;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.Bill;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface BillRepository extends MongoRepository<Bill, String>{
	Bill findByCode(String code);
	Bill findByDescription(String description);
	
}