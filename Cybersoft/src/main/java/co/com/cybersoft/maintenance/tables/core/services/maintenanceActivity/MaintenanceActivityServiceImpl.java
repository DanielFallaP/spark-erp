package co.com.cybersoft.maintenance.tables.core.services.maintenanceActivity;

import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.CreateMaintenanceActivityEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.MaintenanceActivityDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.MaintenanceActivityPageEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.MaintenanceActivityModificationEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.RequestMaintenanceActivityDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.RequestMaintenanceActivityPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.maintenanceActivity.MaintenanceActivityPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class MaintenanceActivityServiceImpl implements MaintenanceActivityService{

	private final MaintenanceActivityPersistenceService maintenanceActivityPersistenceService;
	
	public MaintenanceActivityServiceImpl(final MaintenanceActivityPersistenceService maintenanceActivityPersistenceService){
		this.maintenanceActivityPersistenceService=maintenanceActivityPersistenceService;
	}
	
	/**
	 */
	public MaintenanceActivityDetailsEvent createMaintenanceActivity(CreateMaintenanceActivityEvent event ) throws Exception{
		return maintenanceActivityPersistenceService.createMaintenanceActivity(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public MaintenanceActivityPageEvent requestMaintenanceActivityPage(RequestMaintenanceActivityPageEvent event) throws Exception{
		return maintenanceActivityPersistenceService.requestMaintenanceActivityPage(event);
	}

	public MaintenanceActivityDetailsEvent requestMaintenanceActivityDetails(RequestMaintenanceActivityDetailsEvent event ) throws Exception{
		return maintenanceActivityPersistenceService.requestMaintenanceActivityDetails(event);
	}

	public MaintenanceActivityDetailsEvent modifyMaintenanceActivity(MaintenanceActivityModificationEvent event) throws Exception {
		return maintenanceActivityPersistenceService.modifyMaintenanceActivity(event);
	}
	
	public MaintenanceActivityDetailsEvent requestOnlyRecord() throws Exception {
		return maintenanceActivityPersistenceService.getOnlyRecord();
	}
	
	public MaintenanceActivityPageEvent requestMaintenanceActivityFilterPage(RequestMaintenanceActivityPageEvent event) throws Exception {
		return maintenanceActivityPersistenceService.requestMaintenanceActivityFilterPage(event);
	}
	
	public MaintenanceActivityPageEvent requestMaintenanceActivityFilter(RequestMaintenanceActivityPageEvent event) throws Exception {
		return maintenanceActivityPersistenceService.requestMaintenanceActivityFilter(event);
	}
	

	public MaintenanceActivityPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return maintenanceActivityPersistenceService.requestAllByCompany(fields);
	}public MaintenanceActivityPageEvent requestAllByNameMaintenanceActivity(EmbeddedField... fields) throws Exception {
		return maintenanceActivityPersistenceService.requestAllByNameMaintenanceActivity(fields);
	}public MaintenanceActivityPageEvent requestAllByDurationUnitStandard(EmbeddedField... fields) throws Exception {
		return maintenanceActivityPersistenceService.requestAllByDurationUnitStandard(fields);
	}
	
	
	
}