package co.com.cybersoft.maintenance.tables.core.services.typeMaintenance;

import co.com.cybersoft.maintenance.tables.events.typeMaintenance.CreateTypeMaintenanceEvent;
import co.com.cybersoft.maintenance.tables.events.typeMaintenance.TypeMaintenanceDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeMaintenance.TypeMaintenancePageEvent;
import co.com.cybersoft.maintenance.tables.events.typeMaintenance.TypeMaintenanceModificationEvent;
import co.com.cybersoft.maintenance.tables.events.typeMaintenance.RequestTypeMaintenanceDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeMaintenance.RequestTypeMaintenancePageEvent;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface TypeMaintenanceService {
	TypeMaintenanceDetailsEvent requestOnlyRecord() throws Exception;

	TypeMaintenanceDetailsEvent createTypeMaintenance(CreateTypeMaintenanceEvent event ) throws Exception;
	
	TypeMaintenancePageEvent requestTypeMaintenancePage(RequestTypeMaintenancePageEvent event) throws Exception;

	TypeMaintenanceDetailsEvent requestTypeMaintenanceDetails(RequestTypeMaintenanceDetailsEvent event ) throws Exception;

	TypeMaintenanceDetailsEvent modifyTypeMaintenance(TypeMaintenanceModificationEvent event) throws Exception;
	
	TypeMaintenancePageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	TypeMaintenancePageEvent requestAllByNameTypeMaintenance(EmbeddedField... fields) throws Exception;

	
	
	TypeMaintenancePageEvent requestTypeMaintenanceFilterPage(RequestTypeMaintenancePageEvent event) throws Exception;

	TypeMaintenancePageEvent requestTypeMaintenanceFilter(RequestTypeMaintenancePageEvent event) throws Exception;
	
}