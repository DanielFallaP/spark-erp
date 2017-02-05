package co.com.cybersoft.maintenance.tables.core.services.maintenanceWork;

import co.com.cybersoft.maintenance.tables.events.maintenanceWork.CreateMaintenanceWorkEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.MaintenanceWorkDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.MaintenanceWorkPageEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.MaintenanceWorkModificationEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.RequestMaintenanceWorkDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.RequestMaintenanceWorkPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.maintenanceWork.MaintenanceWorkPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class MaintenanceWorkServiceImpl implements MaintenanceWorkService{

	private final MaintenanceWorkPersistenceService maintenanceWorkPersistenceService;
	
	public MaintenanceWorkServiceImpl(final MaintenanceWorkPersistenceService maintenanceWorkPersistenceService){
		this.maintenanceWorkPersistenceService=maintenanceWorkPersistenceService;
	}
	
	/**
	 */
	public MaintenanceWorkDetailsEvent createMaintenanceWork(CreateMaintenanceWorkEvent event ) throws Exception{
		return maintenanceWorkPersistenceService.createMaintenanceWork(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public MaintenanceWorkPageEvent requestMaintenanceWorkPage(RequestMaintenanceWorkPageEvent event) throws Exception{
		return maintenanceWorkPersistenceService.requestMaintenanceWorkPage(event);
	}

	public MaintenanceWorkDetailsEvent requestMaintenanceWorkDetails(RequestMaintenanceWorkDetailsEvent event ) throws Exception{
		return maintenanceWorkPersistenceService.requestMaintenanceWorkDetails(event);
	}

	public MaintenanceWorkDetailsEvent modifyMaintenanceWork(MaintenanceWorkModificationEvent event) throws Exception {
		return maintenanceWorkPersistenceService.modifyMaintenanceWork(event);
	}
	
	public MaintenanceWorkDetailsEvent requestOnlyRecord() throws Exception {
		return maintenanceWorkPersistenceService.getOnlyRecord();
	}
	
	public MaintenanceWorkPageEvent requestMaintenanceWorkFilterPage(RequestMaintenanceWorkPageEvent event) throws Exception {
		return maintenanceWorkPersistenceService.requestMaintenanceWorkFilterPage(event);
	}
	
	public MaintenanceWorkPageEvent requestMaintenanceWorkFilter(RequestMaintenanceWorkPageEvent event) throws Exception {
		return maintenanceWorkPersistenceService.requestMaintenanceWorkFilter(event);
	}
	

	public MaintenanceWorkPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return maintenanceWorkPersistenceService.requestAllByCompany(fields);
	}public MaintenanceWorkPageEvent requestAllByNameMaintenanceWork(EmbeddedField... fields) throws Exception {
		return maintenanceWorkPersistenceService.requestAllByNameMaintenanceWork(fields);
	}
	
	
	
}