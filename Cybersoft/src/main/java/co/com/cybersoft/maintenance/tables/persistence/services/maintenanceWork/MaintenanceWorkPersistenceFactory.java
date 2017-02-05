package co.com.cybersoft.maintenance.tables.persistence.services.maintenanceWork;

import co.com.cybersoft.maintenance.tables.persistence.repository.maintenanceWork.MaintenanceWorkRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class MaintenanceWorkPersistenceFactory {

	MaintenanceWorkRepository maintenanceWorkRepository;
	
	public MaintenanceWorkPersistenceFactory(MaintenanceWorkRepository maintenanceWorkRepository){
		this.maintenanceWorkRepository=maintenanceWorkRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.MaintenanceWork getMaintenanceWorkByField(String field, String value){
		if (field.equals("nameMaintenanceWork"))
					return maintenanceWorkRepository.findByNameMaintenanceWork(value);		
		return null;
	}
}