package co.com.cybersoft.maintenance.tables.events.maintenanceActivity;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.MaintenanceActivity;
import co.com.cybersoft.maintenance.tables.core.domain.MaintenanceActivityDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.MaintenanceActivity;
import java.util.List;

/**
 * Event class for MaintenanceActivity
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class MaintenanceActivityPageEvent {
	private Page<MaintenanceActivity> maintenanceActivityPage;
	
	private List<MaintenanceActivity> allList;
	
	private Long totalCount;
	
	private List<MaintenanceActivityDetails> maintenanceActivityList;



	
	public MaintenanceActivityPageEvent(){
    }
	public MaintenanceActivityPageEvent(List<MaintenanceActivityDetails>  maintenanceActivityList){
			this.maintenanceActivityList= maintenanceActivityList;
	}



	
	public List<MaintenanceActivityDetails> getMaintenanceActivityList() {
	return maintenanceActivityList;
	}



	
	public List<MaintenanceActivity> getAllList() {
		return allList;
	}

	public void setAllList(List<MaintenanceActivity> allList) {
		this.allList = allList;
	}
	
	public MaintenanceActivityPageEvent(Page<MaintenanceActivity>  maintenanceActivityPage){
		this.maintenanceActivityPage= maintenanceActivityPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public MaintenanceActivityPageEvent(Page<MaintenanceActivity>  maintenanceActivityPage, Long totalCount){
		this.maintenanceActivityPage= maintenanceActivityPage;
		this.totalCount=totalCount;
	}

	public Page<MaintenanceActivity> getMaintenanceActivityPage() {
		return maintenanceActivityPage;
	}
	
	
}