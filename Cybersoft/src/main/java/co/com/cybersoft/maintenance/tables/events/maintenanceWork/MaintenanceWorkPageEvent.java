package co.com.cybersoft.maintenance.tables.events.maintenanceWork;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.MaintenanceWork;
import co.com.cybersoft.maintenance.tables.core.domain.MaintenanceWorkDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.MaintenanceWork;
import java.util.List;

/**
 * Event class for MaintenanceWork
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class MaintenanceWorkPageEvent {
	private Page<MaintenanceWork> maintenanceWorkPage;
	
	private List<MaintenanceWork> allList;
	
	private Long totalCount;
	
	private List<MaintenanceWorkDetails> maintenanceWorkList;



	
	public MaintenanceWorkPageEvent(){
    }
	public MaintenanceWorkPageEvent(List<MaintenanceWorkDetails>  maintenanceWorkList){
			this.maintenanceWorkList= maintenanceWorkList;
	}



	
	public List<MaintenanceWorkDetails> getMaintenanceWorkList() {
	return maintenanceWorkList;
	}



	
	public List<MaintenanceWork> getAllList() {
		return allList;
	}

	public void setAllList(List<MaintenanceWork> allList) {
		this.allList = allList;
	}
	
	public MaintenanceWorkPageEvent(Page<MaintenanceWork>  maintenanceWorkPage){
		this.maintenanceWorkPage= maintenanceWorkPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public MaintenanceWorkPageEvent(Page<MaintenanceWork>  maintenanceWorkPage, Long totalCount){
		this.maintenanceWorkPage= maintenanceWorkPage;
		this.totalCount=totalCount;
	}

	public Page<MaintenanceWork> getMaintenanceWorkPage() {
		return maintenanceWorkPage;
	}
	
	
}