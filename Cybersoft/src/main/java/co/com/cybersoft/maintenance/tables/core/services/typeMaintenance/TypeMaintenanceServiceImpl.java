package co.com.cybersoft.maintenance.tables.core.services.typeMaintenance;

import co.com.cybersoft.maintenance.tables.events.typeMaintenance.CreateTypeMaintenanceEvent;
import co.com.cybersoft.maintenance.tables.events.typeMaintenance.TypeMaintenanceDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeMaintenance.TypeMaintenancePageEvent;
import co.com.cybersoft.maintenance.tables.events.typeMaintenance.TypeMaintenanceModificationEvent;
import co.com.cybersoft.maintenance.tables.events.typeMaintenance.RequestTypeMaintenanceDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeMaintenance.RequestTypeMaintenancePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.typeMaintenance.TypeMaintenancePersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class TypeMaintenanceServiceImpl implements TypeMaintenanceService{

	private final TypeMaintenancePersistenceService typeMaintenancePersistenceService;
	
	public TypeMaintenanceServiceImpl(final TypeMaintenancePersistenceService typeMaintenancePersistenceService){
		this.typeMaintenancePersistenceService=typeMaintenancePersistenceService;
	}
	
	/**
	 */
	public TypeMaintenanceDetailsEvent createTypeMaintenance(CreateTypeMaintenanceEvent event ) throws Exception{
		return typeMaintenancePersistenceService.createTypeMaintenance(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public TypeMaintenancePageEvent requestTypeMaintenancePage(RequestTypeMaintenancePageEvent event) throws Exception{
		return typeMaintenancePersistenceService.requestTypeMaintenancePage(event);
	}

	public TypeMaintenanceDetailsEvent requestTypeMaintenanceDetails(RequestTypeMaintenanceDetailsEvent event ) throws Exception{
		return typeMaintenancePersistenceService.requestTypeMaintenanceDetails(event);
	}

	public TypeMaintenanceDetailsEvent modifyTypeMaintenance(TypeMaintenanceModificationEvent event) throws Exception {
		return typeMaintenancePersistenceService.modifyTypeMaintenance(event);
	}
	
	public TypeMaintenanceDetailsEvent requestOnlyRecord() throws Exception {
		return typeMaintenancePersistenceService.getOnlyRecord();
	}
	
	public TypeMaintenancePageEvent requestTypeMaintenanceFilterPage(RequestTypeMaintenancePageEvent event) throws Exception {
		return typeMaintenancePersistenceService.requestTypeMaintenanceFilterPage(event);
	}
	
	public TypeMaintenancePageEvent requestTypeMaintenanceFilter(RequestTypeMaintenancePageEvent event) throws Exception {
		return typeMaintenancePersistenceService.requestTypeMaintenanceFilter(event);
	}
	

	public TypeMaintenancePageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return typeMaintenancePersistenceService.requestAllByCompany(fields);
	}public TypeMaintenancePageEvent requestAllByNameTypeMaintenance(EmbeddedField... fields) throws Exception {
		return typeMaintenancePersistenceService.requestAllByNameTypeMaintenance(fields);
	}
	
	
	
}