package co.com.cybersoft.docsTest.persistence.repository.requisition;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.docsTest.persistence.domain.Requisition;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface RequisitionRepository extends MongoRepository<Requisition, String>{

	Requisition findByActive(Boolean value);
	
	Requisition findByNumericId(Long value);

}