package co.com.cybersoft.maintenance.tables.events.typeMaintenance;

import co.com.cybersoft.maintenance.tables.core.domain.TypeMaintenanceDetails;

/**
 * Event class for TypeMaintenance
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class TypeMaintenanceDetailsEvent {
	
	private TypeMaintenanceDetails typeMaintenanceDetails;
	
	public TypeMaintenanceDetailsEvent(TypeMaintenanceDetails typeMaintenanceDetails){
		this.typeMaintenanceDetails=typeMaintenanceDetails;
	}

	public TypeMaintenanceDetails getTypeMaintenanceDetails() {
		return typeMaintenanceDetails;
	}

}