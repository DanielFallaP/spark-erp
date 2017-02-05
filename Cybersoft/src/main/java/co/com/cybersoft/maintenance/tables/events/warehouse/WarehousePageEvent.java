package co.com.cybersoft.maintenance.tables.events.warehouse;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.Warehouse;
import co.com.cybersoft.maintenance.tables.core.domain.WarehouseDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.Warehouse;
import java.util.List;

/**
 * Event class for Warehouse
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class WarehousePageEvent {
	private Page<Warehouse> warehousePage;
	
	private List<Warehouse> allList;
	
	private Long totalCount;
	
	private List<WarehouseDetails> warehouseList;



	
	public WarehousePageEvent(){
    }
	public WarehousePageEvent(List<WarehouseDetails>  warehouseList){
			this.warehouseList= warehouseList;
	}



	
	public List<WarehouseDetails> getWarehouseList() {
	return warehouseList;
	}



	
	public List<Warehouse> getAllList() {
		return allList;
	}

	public void setAllList(List<Warehouse> allList) {
		this.allList = allList;
	}
	
	public WarehousePageEvent(Page<Warehouse>  warehousePage){
		this.warehousePage= warehousePage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public WarehousePageEvent(Page<Warehouse>  warehousePage, Long totalCount){
		this.warehousePage= warehousePage;
		this.totalCount=totalCount;
	}

	public Page<Warehouse> getWarehousePage() {
		return warehousePage;
	}
	
	
}