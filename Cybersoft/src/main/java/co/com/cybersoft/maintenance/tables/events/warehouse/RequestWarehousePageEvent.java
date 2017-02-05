package co.com.cybersoft.maintenance.tables.events.warehouse;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.warehouse.WarehouseFilterInfo;

/**
 * Event class for Warehouse
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestWarehousePageEvent {

	private Pageable pageable;
	private WarehouseFilterInfo filter;
	
	public RequestWarehousePageEvent(Pageable pageable, WarehouseFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestWarehousePageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public WarehouseFilterInfo getFilter() {
		return filter;
	}
}