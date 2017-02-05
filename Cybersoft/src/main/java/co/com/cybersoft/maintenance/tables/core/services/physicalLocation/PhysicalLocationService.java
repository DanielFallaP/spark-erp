package co.com.cybersoft.maintenance.tables.core.services.physicalLocation;

import co.com.cybersoft.maintenance.tables.events.physicalLocation.CreatePhysicalLocationEvent;
import co.com.cybersoft.maintenance.tables.events.physicalLocation.PhysicalLocationDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.physicalLocation.PhysicalLocationPageEvent;
import co.com.cybersoft.maintenance.tables.events.physicalLocation.PhysicalLocationModificationEvent;
import co.com.cybersoft.maintenance.tables.events.physicalLocation.RequestPhysicalLocationDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.physicalLocation.RequestPhysicalLocationPageEvent;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface PhysicalLocationService {
	PhysicalLocationDetailsEvent requestOnlyRecord() throws Exception;

	PhysicalLocationDetailsEvent createPhysicalLocation(CreatePhysicalLocationEvent event ) throws Exception;
	
	PhysicalLocationPageEvent requestPhysicalLocationPage(RequestPhysicalLocationPageEvent event) throws Exception;

	PhysicalLocationDetailsEvent requestPhysicalLocationDetails(RequestPhysicalLocationDetailsEvent event ) throws Exception;

	PhysicalLocationDetailsEvent modifyPhysicalLocation(PhysicalLocationModificationEvent event) throws Exception;
	
	PhysicalLocationPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	PhysicalLocationPageEvent requestAllByNamePhysicalLocation(EmbeddedField... fields) throws Exception;
	PhysicalLocationPageEvent requestAllByDescription(EmbeddedField... fields) throws Exception;
	PhysicalLocationPageEvent requestAllByDescriptionEnglish(EmbeddedField... fields) throws Exception;
	PhysicalLocationPageEvent requestAllByDescriptionShort(EmbeddedField... fields) throws Exception;
	PhysicalLocationPageEvent requestAllByMeasurementUnit(EmbeddedField... fields) throws Exception;
	PhysicalLocationPageEvent requestAllByCapacityPhysicalLocation(EmbeddedField... fields) throws Exception;

	
	
	PhysicalLocationPageEvent requestPhysicalLocationFilterPage(RequestPhysicalLocationPageEvent event) throws Exception;

	PhysicalLocationPageEvent requestPhysicalLocationFilter(RequestPhysicalLocationPageEvent event) throws Exception;
	
}