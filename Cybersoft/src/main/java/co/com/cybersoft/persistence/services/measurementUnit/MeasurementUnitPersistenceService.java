package co.com.cybersoft.persistence.services.measurementUnit;

import co.com.cybersoft.events.measurementUnit.CreateMeasurementUnitEvent;
import co.com.cybersoft.events.measurementUnit.MeasurementUnitDetailsEvent;
import co.com.cybersoft.events.measurementUnit.MeasurementUnitPageEvent;
import co.com.cybersoft.events.measurementUnit.MeasurementUnitModificationEvent;
import co.com.cybersoft.events.measurementUnit.RequestMeasurementUnitDetailsEvent;
import co.com.cybersoft.events.measurementUnit.RequestMeasurementUnitPageEvent;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface MeasurementUnitPersistenceService {

	MeasurementUnitDetailsEvent createMeasurementUnit(CreateMeasurementUnitEvent event) throws Exception;

	MeasurementUnitPageEvent requestMeasurementUnitPage(RequestMeasurementUnitPageEvent event) throws Exception;

	MeasurementUnitDetailsEvent requestMeasurementUnitDetails(RequestMeasurementUnitDetailsEvent event) throws Exception;
	
	MeasurementUnitDetailsEvent modifyMeasurementUnit(MeasurementUnitModificationEvent event) throws Exception;
	
	MeasurementUnitPageEvent requestAll() throws Exception;
}