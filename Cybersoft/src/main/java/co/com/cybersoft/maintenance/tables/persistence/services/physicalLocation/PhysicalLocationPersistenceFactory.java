package co.com.cybersoft.maintenance.tables.persistence.services.physicalLocation;

import co.com.cybersoft.maintenance.tables.persistence.repository.physicalLocation.PhysicalLocationRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class PhysicalLocationPersistenceFactory {

	PhysicalLocationRepository physicalLocationRepository;
	
	public PhysicalLocationPersistenceFactory(PhysicalLocationRepository physicalLocationRepository){
		this.physicalLocationRepository=physicalLocationRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.PhysicalLocation getPhysicalLocationByField(String field, String value){
		if (field.equals("namePhysicalLocation"))
					return physicalLocationRepository.findByNamePhysicalLocation(value);if (field.equals("description"))
					return physicalLocationRepository.findByDescription(value);if (field.equals("descriptionEnglish"))
					return physicalLocationRepository.findByDescriptionEnglish(value);if (field.equals("descriptionShort"))
					return physicalLocationRepository.findByDescriptionShort(value);		
		return null;
	}
}