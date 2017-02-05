package co.com.cybersoft.maintenance.tables.persistence.services.maintenanceActivity;

import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.CreateMaintenanceActivityEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.MaintenanceActivityDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.MaintenanceActivityPageEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.MaintenanceActivityModificationEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.RequestMaintenanceActivityDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.RequestMaintenanceActivityPageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface MaintenanceActivityPersistenceService {

	MaintenanceActivityDetailsEvent createMaintenanceActivity(CreateMaintenanceActivityEvent event) throws Exception;

	MaintenanceActivityPageEvent requestMaintenanceActivityPage(RequestMaintenanceActivityPageEvent event) throws Exception;

	MaintenanceActivityDetailsEvent requestMaintenanceActivityDetails(RequestMaintenanceActivityDetailsEvent event) throws Exception;
	
	MaintenanceActivityDetailsEvent modifyMaintenanceActivity(MaintenanceActivityModificationEvent event) throws Exception;
	MaintenanceActivityPageEvent requestMaintenanceActivityFilterPage(RequestMaintenanceActivityPageEvent event) throws Exception;
	MaintenanceActivityPageEvent requestMaintenanceActivityFilter(RequestMaintenanceActivityPageEvent event) throws Exception;
	
	MaintenanceActivityPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	MaintenanceActivityPageEvent requestAllByNameMaintenanceActivity(EmbeddedField... fields) throws Exception;
	MaintenanceActivityPageEvent requestAllByDurationUnitStandard(EmbeddedField... fields) throws Exception;

	
	
	MaintenanceActivityDetailsEvent getOnlyRecord() throws Exception;
	
}