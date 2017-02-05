package co.com.cybersoft.maintenance.tables.persistence.services.measurementUnit;

import co.com.cybersoft.maintenance.tables.events.measurementUnit.CreateMeasurementUnitEvent;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.MeasurementUnitDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.MeasurementUnitPageEvent;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.MeasurementUnitModificationEvent;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.RequestMeasurementUnitDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.RequestMeasurementUnitPageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface MeasurementUnitPersistenceService {

	MeasurementUnitDetailsEvent createMeasurementUnit(CreateMeasurementUnitEvent event) throws Exception;

	MeasurementUnitPageEvent requestMeasurementUnitPage(RequestMeasurementUnitPageEvent event) throws Exception;

	MeasurementUnitDetailsEvent requestMeasurementUnitDetails(RequestMeasurementUnitDetailsEvent event) throws Exception;
	
	MeasurementUnitDetailsEvent modifyMeasurementUnit(MeasurementUnitModificationEvent event) throws Exception;
	MeasurementUnitPageEvent requestMeasurementUnitFilterPage(RequestMeasurementUnitPageEvent event) throws Exception;
	MeasurementUnitPageEvent requestMeasurementUnitFilter(RequestMeasurementUnitPageEvent event) throws Exception;
	
	MeasurementUnitPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	MeasurementUnitPageEvent requestAllByNameUnit(EmbeddedField... fields) throws Exception;
	MeasurementUnitPageEvent requestAllByAbbreviationUnit(EmbeddedField... fields) throws Exception;

	
	
	MeasurementUnitDetailsEvent getOnlyRecord() throws Exception;
	
}