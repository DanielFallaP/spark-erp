package co.com.cybersoft.maintenance.tables.persistence.services.measurementUnit;

import co.com.cybersoft.maintenance.tables.persistence.repository.measurementUnit.MeasurementUnitRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class MeasurementUnitPersistenceFactory {

	MeasurementUnitRepository measurementUnitRepository;
	
	public MeasurementUnitPersistenceFactory(MeasurementUnitRepository measurementUnitRepository){
		this.measurementUnitRepository=measurementUnitRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.MeasurementUnit getMeasurementUnitByField(String field, String value){
		if (field.equals("nameUnit"))
					return measurementUnitRepository.findByNameUnit(value);if (field.equals("abbreviationUnit"))
					return measurementUnitRepository.findByAbbreviationUnit(value);		
		return null;
	}
}