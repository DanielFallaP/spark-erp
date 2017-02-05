package co.com.cybersoft.maintenance.tables.persistence.services.warehouse;

import co.com.cybersoft.maintenance.tables.persistence.repository.warehouse.WarehouseRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class WarehousePersistenceFactory {

	WarehouseRepository warehouseRepository;
	
	public WarehousePersistenceFactory(WarehouseRepository warehouseRepository){
		this.warehouseRepository=warehouseRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.Warehouse getWarehouseByField(String field, String value){
		if (field.equals("storeName"))
					return warehouseRepository.findByStoreName(value);if (field.equals("comment"))
					return warehouseRepository.findByComment(value);		
		return null;
	}
}