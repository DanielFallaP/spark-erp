package co.com.cybersoft.maintenance.tables.events.maintenanceWork;

import co.com.cybersoft.maintenance.tables.core.domain.MaintenanceWorkDetails;

/**
 * Event class for MaintenanceWork
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class MaintenanceWorkDetailsEvent {
	
	private MaintenanceWorkDetails maintenanceWorkDetails;
	
	public MaintenanceWorkDetailsEvent(MaintenanceWorkDetails maintenanceWorkDetails){
		this.maintenanceWorkDetails=maintenanceWorkDetails;
	}

	public MaintenanceWorkDetails getMaintenanceWorkDetails() {
		return maintenanceWorkDetails;
	}

}