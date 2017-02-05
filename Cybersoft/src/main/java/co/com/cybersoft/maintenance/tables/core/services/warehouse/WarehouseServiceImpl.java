package co.com.cybersoft.maintenance.tables.core.services.warehouse;

import co.com.cybersoft.maintenance.tables.events.warehouse.CreateWarehouseEvent;
import co.com.cybersoft.maintenance.tables.events.warehouse.WarehouseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.warehouse.WarehousePageEvent;
import co.com.cybersoft.maintenance.tables.events.warehouse.WarehouseModificationEvent;
import co.com.cybersoft.maintenance.tables.events.warehouse.RequestWarehouseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.warehouse.RequestWarehousePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.warehouse.WarehousePersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class WarehouseServiceImpl implements WarehouseService{

	private final WarehousePersistenceService warehousePersistenceService;
	
	public WarehouseServiceImpl(final WarehousePersistenceService warehousePersistenceService){
		this.warehousePersistenceService=warehousePersistenceService;
	}
	
	/**
	 */
	public WarehouseDetailsEvent createWarehouse(CreateWarehouseEvent event ) throws Exception{
		return warehousePersistenceService.createWarehouse(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public WarehousePageEvent requestWarehousePage(RequestWarehousePageEvent event) throws Exception{
		return warehousePersistenceService.requestWarehousePage(event);
	}

	public WarehouseDetailsEvent requestWarehouseDetails(RequestWarehouseDetailsEvent event ) throws Exception{
		return warehousePersistenceService.requestWarehouseDetails(event);
	}

	public WarehouseDetailsEvent modifyWarehouse(WarehouseModificationEvent event) throws Exception {
		return warehousePersistenceService.modifyWarehouse(event);
	}
	
	public WarehouseDetailsEvent requestOnlyRecord() throws Exception {
		return warehousePersistenceService.getOnlyRecord();
	}
	
	public WarehousePageEvent requestWarehouseFilterPage(RequestWarehousePageEvent event) throws Exception {
		return warehousePersistenceService.requestWarehouseFilterPage(event);
	}
	
	public WarehousePageEvent requestWarehouseFilter(RequestWarehousePageEvent event) throws Exception {
		return warehousePersistenceService.requestWarehouseFilter(event);
	}
	

	public WarehousePageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return warehousePersistenceService.requestAllByCompany(fields);
	}public WarehousePageEvent requestAllByStoreName(EmbeddedField... fields) throws Exception {
		return warehousePersistenceService.requestAllByStoreName(fields);
	}public WarehousePageEvent requestAllByPhysicalLocation(EmbeddedField... fields) throws Exception {
		return warehousePersistenceService.requestAllByPhysicalLocation(fields);
	}public WarehousePageEvent requestAllByCostingType(EmbeddedField... fields) throws Exception {
		return warehousePersistenceService.requestAllByCostingType(fields);
	}public WarehousePageEvent requestAllByComment(EmbeddedField... fields) throws Exception {
		return warehousePersistenceService.requestAllByComment(fields);
	}public WarehousePageEvent requestAllByStoreType(EmbeddedField... fields) throws Exception {
		return warehousePersistenceService.requestAllByStoreType(fields);
	}
	
	
	
}