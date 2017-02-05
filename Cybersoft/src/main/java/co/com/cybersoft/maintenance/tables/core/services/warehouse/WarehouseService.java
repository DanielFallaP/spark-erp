package co.com.cybersoft.maintenance.tables.core.services.warehouse;

import co.com.cybersoft.maintenance.tables.events.warehouse.CreateWarehouseEvent;
import co.com.cybersoft.maintenance.tables.events.warehouse.WarehouseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.warehouse.WarehousePageEvent;
import co.com.cybersoft.maintenance.tables.events.warehouse.WarehouseModificationEvent;
import co.com.cybersoft.maintenance.tables.events.warehouse.RequestWarehouseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.warehouse.RequestWarehousePageEvent;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface WarehouseService {
	WarehouseDetailsEvent requestOnlyRecord() throws Exception;

	WarehouseDetailsEvent createWarehouse(CreateWarehouseEvent event ) throws Exception;
	
	WarehousePageEvent requestWarehousePage(RequestWarehousePageEvent event) throws Exception;

	WarehouseDetailsEvent requestWarehouseDetails(RequestWarehouseDetailsEvent event ) throws Exception;

	WarehouseDetailsEvent modifyWarehouse(WarehouseModificationEvent event) throws Exception;
	
	WarehousePageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	WarehousePageEvent requestAllByStoreName(EmbeddedField... fields) throws Exception;
	WarehousePageEvent requestAllByPhysicalLocation(EmbeddedField... fields) throws Exception;
	WarehousePageEvent requestAllByCostingType(EmbeddedField... fields) throws Exception;
	WarehousePageEvent requestAllByComment(EmbeddedField... fields) throws Exception;
	WarehousePageEvent requestAllByStoreType(EmbeddedField... fields) throws Exception;

	
	
	WarehousePageEvent requestWarehouseFilterPage(RequestWarehousePageEvent event) throws Exception;

	WarehousePageEvent requestWarehouseFilter(RequestWarehousePageEvent event) throws Exception;
	
}