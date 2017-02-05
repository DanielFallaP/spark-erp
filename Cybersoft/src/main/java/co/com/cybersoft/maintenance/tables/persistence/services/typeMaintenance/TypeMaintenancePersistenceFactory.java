package co.com.cybersoft.maintenance.tables.persistence.services.typeMaintenance;

import co.com.cybersoft.maintenance.tables.persistence.repository.typeMaintenance.TypeMaintenanceRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class TypeMaintenancePersistenceFactory {

	TypeMaintenanceRepository typeMaintenanceRepository;
	
	public TypeMaintenancePersistenceFactory(TypeMaintenanceRepository typeMaintenanceRepository){
		this.typeMaintenanceRepository=typeMaintenanceRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.TypeMaintenance getTypeMaintenanceByField(String field, String value){
		if (field.equals("nameTypeMaintenance"))
					return typeMaintenanceRepository.findByNameTypeMaintenance(value);		
		return null;
	}
}