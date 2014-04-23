package co.com.cybersoft.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.MeasurementUnits;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface MeasurementUnitsRepository extends MongoRepository<MeasurementUnits, String>{
	MeasurementUnits findByCode(String code);
}