package co.com.cybersoft.maintenance.tables.events.stateCostCenters;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.StateCostCenters;
import co.com.cybersoft.maintenance.tables.core.domain.StateCostCentersDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.StateCostCenters;
import java.util.List;

/**
 * Event class for StateCostCenters
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class StateCostCentersPageEvent {
	private Page<StateCostCenters> stateCostCentersPage;
	
	private List<StateCostCenters> allList;
	
	private Long totalCount;
	
	private List<StateCostCentersDetails> stateCostCentersList;



	
	public StateCostCentersPageEvent(){
    }
	public StateCostCentersPageEvent(List<StateCostCentersDetails>  stateCostCentersList){
			this.stateCostCentersList= stateCostCentersList;
	}



	
	public List<StateCostCentersDetails> getStateCostCentersList() {
	return stateCostCentersList;
	}



	
	public List<StateCostCenters> getAllList() {
		return allList;
	}

	public void setAllList(List<StateCostCenters> allList) {
		this.allList = allList;
	}
	
	public StateCostCentersPageEvent(Page<StateCostCenters>  stateCostCentersPage){
		this.stateCostCentersPage= stateCostCentersPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public StateCostCentersPageEvent(Page<StateCostCenters>  stateCostCentersPage, Long totalCount){
		this.stateCostCentersPage= stateCostCentersPage;
		this.totalCount=totalCount;
	}

	public Page<StateCostCenters> getStateCostCentersPage() {
		return stateCostCentersPage;
	}
	
	
}