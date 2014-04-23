package co.com.cybersoft.persistence.services.measurementUnits;

import co.com.cybersoft.events.measurementUnits.CreateMeasurementUnitsEvent;
import co.com.cybersoft.events.measurementUnits.MeasurementUnitsDetailsEvent;
import co.com.cybersoft.events.measurementUnits.MeasurementUnitsPageEvent;
import co.com.cybersoft.events.measurementUnits.MeasurementUnitsModificationEvent;
import co.com.cybersoft.events.measurementUnits.RequestMeasurementUnitsDetailsEvent;
import co.com.cybersoft.events.measurementUnits.RequestMeasurementUnitsPageEvent;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface MeasurementUnitsPersistenceService {

	MeasurementUnitsDetailsEvent createMeasurementUnits(CreateMeasurementUnitsEvent event) throws Exception;

	MeasurementUnitsPageEvent requestMeasurementUnitsPage(RequestMeasurementUnitsPageEvent event) throws Exception;

	MeasurementUnitsDetailsEvent requestMeasurementUnitsDetails(RequestMeasurementUnitsDetailsEvent event) throws Exception;
	
	MeasurementUnitsDetailsEvent modifyMeasurementUnits(MeasurementUnitsModificationEvent event) throws Exception;
	
	MeasurementUnitsPageEvent requestAll() throws Exception;
	
}