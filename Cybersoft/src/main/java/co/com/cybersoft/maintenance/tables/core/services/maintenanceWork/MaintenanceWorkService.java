package co.com.cybersoft.maintenance.tables.core.services.maintenanceWork;

import co.com.cybersoft.maintenance.tables.events.maintenanceWork.CreateMaintenanceWorkEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.MaintenanceWorkDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.MaintenanceWorkPageEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.MaintenanceWorkModificationEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.RequestMaintenanceWorkDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.RequestMaintenanceWorkPageEvent;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface MaintenanceWorkService {
	MaintenanceWorkDetailsEvent requestOnlyRecord() throws Exception;

	MaintenanceWorkDetailsEvent createMaintenanceWork(CreateMaintenanceWorkEvent event ) throws Exception;
	
	MaintenanceWorkPageEvent requestMaintenanceWorkPage(RequestMaintenanceWorkPageEvent event) throws Exception;

	MaintenanceWorkDetailsEvent requestMaintenanceWorkDetails(RequestMaintenanceWorkDetailsEvent event ) throws Exception;

	MaintenanceWorkDetailsEvent modifyMaintenanceWork(MaintenanceWorkModificationEvent event) throws Exception;
	
	MaintenanceWorkPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	MaintenanceWorkPageEvent requestAllByNameMaintenanceWork(EmbeddedField... fields) throws Exception;

	
	
	MaintenanceWorkPageEvent requestMaintenanceWorkFilterPage(RequestMaintenanceWorkPageEvent event) throws Exception;

	MaintenanceWorkPageEvent requestMaintenanceWorkFilter(RequestMaintenanceWorkPageEvent event) throws Exception;
	
}