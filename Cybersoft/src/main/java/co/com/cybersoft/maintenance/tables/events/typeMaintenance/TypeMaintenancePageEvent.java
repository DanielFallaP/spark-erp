package co.com.cybersoft.maintenance.tables.events.typeMaintenance;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.TypeMaintenance;
import co.com.cybersoft.maintenance.tables.core.domain.TypeMaintenanceDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeMaintenance;
import java.util.List;

/**
 * Event class for TypeMaintenance
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class TypeMaintenancePageEvent {
	private Page<TypeMaintenance> typeMaintenancePage;
	
	private List<TypeMaintenance> allList;
	
	private Long totalCount;
	
	private List<TypeMaintenanceDetails> typeMaintenanceList;



	
	public TypeMaintenancePageEvent(){
    }
	public TypeMaintenancePageEvent(List<TypeMaintenanceDetails>  typeMaintenanceList){
			this.typeMaintenanceList= typeMaintenanceList;
	}



	
	public List<TypeMaintenanceDetails> getTypeMaintenanceList() {
	return typeMaintenanceList;
	}



	
	public List<TypeMaintenance> getAllList() {
		return allList;
	}

	public void setAllList(List<TypeMaintenance> allList) {
		this.allList = allList;
	}
	
	public TypeMaintenancePageEvent(Page<TypeMaintenance>  typeMaintenancePage){
		this.typeMaintenancePage= typeMaintenancePage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public TypeMaintenancePageEvent(Page<TypeMaintenance>  typeMaintenancePage, Long totalCount){
		this.typeMaintenancePage= typeMaintenancePage;
		this.totalCount=totalCount;
	}

	public Page<TypeMaintenance> getTypeMaintenancePage() {
		return typeMaintenancePage;
	}
	
	
}