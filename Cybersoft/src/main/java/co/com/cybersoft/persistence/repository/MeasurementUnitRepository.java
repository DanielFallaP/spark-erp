package co.com.cybersoft.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.MeasurementUnit;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface MeasurementUnitRepository extends MongoRepository<MeasurementUnit, String>{
	MeasurementUnit findByCode(String code);
}