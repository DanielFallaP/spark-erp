package co.com.cybersoft.persistence.repository.measurementUnits;

import java.util.List;

import co.com.cybersoft.persistence.domain.MeasurementUnits;

public interface MeasurementUnitsCustomRepo {

	List<MeasurementUnits> findByStartingCodeNumber(String codePrefix);
	
	List<MeasurementUnits> findByContainingDescription(String descriptionSubstring);
}