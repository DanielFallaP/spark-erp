package co.com.cybersoft.persistence.repository.costCenter;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.CostCenter;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface CostCenterRepository extends MongoRepository<CostCenter, String>{
	CostCenter findByCode(String code);
	
}