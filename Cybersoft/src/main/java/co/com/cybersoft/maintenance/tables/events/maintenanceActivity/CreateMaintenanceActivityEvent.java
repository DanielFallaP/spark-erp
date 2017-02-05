package co.com.cybersoft.maintenance.tables.events.maintenanceActivity;

import co.com.cybersoft.maintenance.tables.core.domain.MaintenanceActivityDetails;

/**
 * Event class for MaintenanceActivity
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateMaintenanceActivityEvent {
		
	private MaintenanceActivityDetails maintenanceActivityDetails;
	
	public CreateMaintenanceActivityEvent(MaintenanceActivityDetails maintenanceActivityDetails){
		this.maintenanceActivityDetails=maintenanceActivityDetails;
	}

	public MaintenanceActivityDetails getMaintenanceActivityDetails() {
		return maintenanceActivityDetails;
	}
	
	
}