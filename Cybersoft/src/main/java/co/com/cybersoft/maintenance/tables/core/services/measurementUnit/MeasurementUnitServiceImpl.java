package co.com.cybersoft.maintenance.tables.core.services.measurementUnit;

import co.com.cybersoft.maintenance.tables.events.measurementUnit.CreateMeasurementUnitEvent;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.MeasurementUnitDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.MeasurementUnitPageEvent;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.MeasurementUnitModificationEvent;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.RequestMeasurementUnitDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.RequestMeasurementUnitPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.measurementUnit.MeasurementUnitPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

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
	public MeasurementUnitDetailsEvent createMeasurementUnit(CreateMeasurementUnitEvent event ) throws Exception{
		return measurementUnitPersistenceService.createMeasurementUnit(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public MeasurementUnitPageEvent requestMeasurementUnitPage(RequestMeasurementUnitPageEvent event) throws Exception{
		return measurementUnitPersistenceService.requestMeasurementUnitPage(event);
	}

	public MeasurementUnitDetailsEvent requestMeasurementUnitDetails(RequestMeasurementUnitDetailsEvent event ) throws Exception{
		return measurementUnitPersistenceService.requestMeasurementUnitDetails(event);
	}

	public MeasurementUnitDetailsEvent modifyMeasurementUnit(MeasurementUnitModificationEvent event) throws Exception {
		return measurementUnitPersistenceService.modifyMeasurementUnit(event);
	}
	
	public MeasurementUnitDetailsEvent requestOnlyRecord() throws Exception {
		return measurementUnitPersistenceService.getOnlyRecord();
	}
	
	public MeasurementUnitPageEvent requestMeasurementUnitFilterPage(RequestMeasurementUnitPageEvent event) throws Exception {
		return measurementUnitPersistenceService.requestMeasurementUnitFilterPage(event);
	}
	
	public MeasurementUnitPageEvent requestMeasurementUnitFilter(RequestMeasurementUnitPageEvent event) throws Exception {
		return measurementUnitPersistenceService.requestMeasurementUnitFilter(event);
	}
	

	public MeasurementUnitPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return measurementUnitPersistenceService.requestAllByCompany(fields);
	}public MeasurementUnitPageEvent requestAllByNameUnit(EmbeddedField... fields) throws Exception {
		return measurementUnitPersistenceService.requestAllByNameUnit(fields);
	}public MeasurementUnitPageEvent requestAllByAbbreviationUnit(EmbeddedField... fields) throws Exception {
		return measurementUnitPersistenceService.requestAllByAbbreviationUnit(fields);
	}
	
	
	
}