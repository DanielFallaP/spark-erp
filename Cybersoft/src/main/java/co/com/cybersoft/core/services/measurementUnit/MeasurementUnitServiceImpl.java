package co.com.cybersoft.core.services.measurementUnit;

import java.util.Date;

import co.com.cybersoft.events.measurementUnit.CreateMeasurementUnitEvent;
import co.com.cybersoft.events.measurementUnit.MeasurementUnitDetailsEvent;
import co.com.cybersoft.events.measurementUnit.MeasurementUnitPageEvent;
import co.com.cybersoft.events.measurementUnit.MeasurementUnitModificationEvent;
import co.com.cybersoft.events.measurementUnit.RequestMeasurementUnitDetailsEvent;
import co.com.cybersoft.events.measurementUnit.RequestMeasurementUnitPageEvent;
import co.com.cybersoft.persistence.services.measurementUnit.MeasurementUnitPersistenceService;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class MeasurementUnitServiceImpl implements MeasurementUnitService{

	private final MeasurementUnitPersistenceService measurementUnitPersistenceService;
	
	public MeasurementUnitServiceImpl(final MeasurementUnitPersistenceService measurementUnitPersistenceService){
		this.measurementUnitPersistenceService=measurementUnitPersistenceService;
	}
	
	/**
	 */
	@Override
	public MeasurementUnitDetailsEvent createMeasurementUnit(CreateMeasurementUnitEvent event ) throws Exception{
		event.getMeasurementUnitDetails().setDateOfModification(new Date());
		return measurementUnitPersistenceService.createMeasurementUnit(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	@Override
	public MeasurementUnitPageEvent requestMeasurementUnitPage(RequestMeasurementUnitPageEvent event) throws Exception{
		return measurementUnitPersistenceService.requestMeasurementUnitPage(event);
	}

	@Override
	public MeasurementUnitDetailsEvent requestMeasurementUnitDetails(RequestMeasurementUnitDetailsEvent event ) throws Exception{
		return measurementUnitPersistenceService.requestMeasurementUnitDetails(event);
	}

	@Override
	public MeasurementUnitDetailsEvent modifyMeasurementUnit(MeasurementUnitModificationEvent event) throws Exception {
		event.getMeasurementUnitDetails().setDateOfModification(new Date());
		return measurementUnitPersistenceService.modifyMeasurementUnit(event);
	}

	@Override
	public MeasurementUnitPageEvent requestAll() throws Exception {
		return measurementUnitPersistenceService.requestAll();
	}

}