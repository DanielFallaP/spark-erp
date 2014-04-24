package co.com.cybersoft.core.services.measurementUnits;

import co.com.cybersoft.events.measurementUnits.CreateMeasurementUnitsEvent;
import co.com.cybersoft.events.measurementUnits.MeasurementUnitsDetailsEvent;
import co.com.cybersoft.events.measurementUnits.MeasurementUnitsPageEvent;
import co.com.cybersoft.events.measurementUnits.MeasurementUnitsModificationEvent;
import co.com.cybersoft.events.measurementUnits.RequestMeasurementUnitsDetailsEvent;
import co.com.cybersoft.events.measurementUnits.RequestMeasurementUnitsPageEvent;
import co.com.cybersoft.persistence.services.measurementUnits.MeasurementUnitsPersistenceService;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class MeasurementUnitsServiceImpl implements MeasurementUnitsService{

	private final MeasurementUnitsPersistenceService measurementUnitsPersistenceService;
	
	public MeasurementUnitsServiceImpl(final MeasurementUnitsPersistenceService measurementUnitsPersistenceService){
		this.measurementUnitsPersistenceService=measurementUnitsPersistenceService;
	}
	
	/**
	 */
	@Override
	public MeasurementUnitsDetailsEvent createMeasurementUnits(CreateMeasurementUnitsEvent event ) throws Exception{
		return measurementUnitsPersistenceService.createMeasurementUnits(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	@Override
	public MeasurementUnitsPageEvent requestMeasurementUnitsPage(RequestMeasurementUnitsPageEvent event) throws Exception{
		return measurementUnitsPersistenceService.requestMeasurementUnitsPage(event);
	}

	@Override
	public MeasurementUnitsDetailsEvent requestMeasurementUnitsDetails(RequestMeasurementUnitsDetailsEvent event ) throws Exception{
		return measurementUnitsPersistenceService.requestMeasurementUnitsDetails(event);
	}

	@Override
	public MeasurementUnitsDetailsEvent modifyMeasurementUnits(MeasurementUnitsModificationEvent event) throws Exception {
		return measurementUnitsPersistenceService.modifyMeasurementUnits(event);
	}
	
	@Override
	public MeasurementUnitsPageEvent requestAll() throws Exception {
		return measurementUnitsPersistenceService.requestAll();
	}
	
	@Override
	public MeasurementUnitsPageEvent requestByCodePrefix(String codePrefix) throws Exception {
		return measurementUnitsPersistenceService.requestByCodePrefix(codePrefix);
	}

	@Override
	public MeasurementUnitsPageEvent requestByContainingDescription(String description) throws Exception {
		return measurementUnitsPersistenceService.requestByContainingDescription(description);
	}
	
}