package co.com.cybersoft.maintenance.tables.persistence.services.maintenanceActivity;

import co.com.cybersoft.maintenance.tables.persistence.repository.maintenanceActivity.MaintenanceActivityRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class MaintenanceActivityPersistenceFactory {

	MaintenanceActivityRepository maintenanceActivityRepository;
	
	public MaintenanceActivityPersistenceFactory(MaintenanceActivityRepository maintenanceActivityRepository){
		this.maintenanceActivityRepository=maintenanceActivityRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.MaintenanceActivity getMaintenanceActivityByField(String field, String value){
		if (field.equals("nameMaintenanceActivity"))
					return maintenanceActivityRepository.findByNameMaintenanceActivity(value);if (field.equals("durationUnitStandard"))
					return maintenanceActivityRepository.findByDurationUnitStandard(value);		
		return null;
	}
}