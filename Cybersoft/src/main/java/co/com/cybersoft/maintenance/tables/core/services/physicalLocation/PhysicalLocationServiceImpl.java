package co.com.cybersoft.maintenance.tables.core.services.physicalLocation;

import co.com.cybersoft.maintenance.tables.events.physicalLocation.CreatePhysicalLocationEvent;
import co.com.cybersoft.maintenance.tables.events.physicalLocation.PhysicalLocationDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.physicalLocation.PhysicalLocationPageEvent;
import co.com.cybersoft.maintenance.tables.events.physicalLocation.PhysicalLocationModificationEvent;
import co.com.cybersoft.maintenance.tables.events.physicalLocation.RequestPhysicalLocationDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.physicalLocation.RequestPhysicalLocationPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.physicalLocation.PhysicalLocationPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class PhysicalLocationServiceImpl implements PhysicalLocationService{

	private final PhysicalLocationPersistenceService physicalLocationPersistenceService;
	
	public PhysicalLocationServiceImpl(final PhysicalLocationPersistenceService physicalLocationPersistenceService){
		this.physicalLocationPersistenceService=physicalLocationPersistenceService;
	}
	
	/**
	 */
	public PhysicalLocationDetailsEvent createPhysicalLocation(CreatePhysicalLocationEvent event ) throws Exception{
		return physicalLocationPersistenceService.createPhysicalLocation(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public PhysicalLocationPageEvent requestPhysicalLocationPage(RequestPhysicalLocationPageEvent event) throws Exception{
		return physicalLocationPersistenceService.requestPhysicalLocationPage(event);
	}

	public PhysicalLocationDetailsEvent requestPhysicalLocationDetails(RequestPhysicalLocationDetailsEvent event ) throws Exception{
		return physicalLocationPersistenceService.requestPhysicalLocationDetails(event);
	}

	public PhysicalLocationDetailsEvent modifyPhysicalLocation(PhysicalLocationModificationEvent event) throws Exception {
		return physicalLocationPersistenceService.modifyPhysicalLocation(event);
	}
	
	public PhysicalLocationDetailsEvent requestOnlyRecord() throws Exception {
		return physicalLocationPersistenceService.getOnlyRecord();
	}
	
	public PhysicalLocationPageEvent requestPhysicalLocationFilterPage(RequestPhysicalLocationPageEvent event) throws Exception {
		return physicalLocationPersistenceService.requestPhysicalLocationFilterPage(event);
	}
	
	public PhysicalLocationPageEvent requestPhysicalLocationFilter(RequestPhysicalLocationPageEvent event) throws Exception {
		return physicalLocationPersistenceService.requestPhysicalLocationFilter(event);
	}
	

	public PhysicalLocationPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return physicalLocationPersistenceService.requestAllByCompany(fields);
	}public PhysicalLocationPageEvent requestAllByNamePhysicalLocation(EmbeddedField... fields) throws Exception {
		return physicalLocationPersistenceService.requestAllByNamePhysicalLocation(fields);
	}public PhysicalLocationPageEvent requestAllByDescription(EmbeddedField... fields) throws Exception {
		return physicalLocationPersistenceService.requestAllByDescription(fields);
	}public PhysicalLocationPageEvent requestAllByDescriptionEnglish(EmbeddedField... fields) throws Exception {
		return physicalLocationPersistenceService.requestAllByDescriptionEnglish(fields);
	}public PhysicalLocationPageEvent requestAllByDescriptionShort(EmbeddedField... fields) throws Exception {
		return physicalLocationPersistenceService.requestAllByDescriptionShort(fields);
	}public PhysicalLocationPageEvent requestAllByMeasurementUnit(EmbeddedField... fields) throws Exception {
		return physicalLocationPersistenceService.requestAllByMeasurementUnit(fields);
	}public PhysicalLocationPageEvent requestAllByCapacityPhysicalLocation(EmbeddedField... fields) throws Exception {
		return physicalLocationPersistenceService.requestAllByCapacityPhysicalLocation(fields);
	}
	
	
	
}