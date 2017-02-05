package co.com.cybersoft.maintenance.tables.events.warehouse;

import co.com.cybersoft.maintenance.tables.core.domain.WarehouseDetails;

/**
 * Event class for Warehouse
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class WarehouseDetailsEvent {
	
	private WarehouseDetails warehouseDetails;
	
	public WarehouseDetailsEvent(WarehouseDetails warehouseDetails){
		this.warehouseDetails=warehouseDetails;
	}

	public WarehouseDetails getWarehouseDetails() {
		return warehouseDetails;
	}

}