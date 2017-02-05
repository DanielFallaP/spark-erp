package co.com.cybersoft.maintenance.tables.events.costingType;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.CostingType;
import co.com.cybersoft.maintenance.tables.core.domain.CostingTypeDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.CostingType;
import java.util.List;

/**
 * Event class for CostingType
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class CostingTypePageEvent {
	private Page<CostingType> costingTypePage;
	
	private List<CostingType> allList;
	
	private Long totalCount;
	
	private List<CostingTypeDetails> costingTypeList;



	
	public CostingTypePageEvent(){
    }
	public CostingTypePageEvent(List<CostingTypeDetails>  costingTypeList){
			this.costingTypeList= costingTypeList;
	}



	
	public List<CostingTypeDetails> getCostingTypeList() {
	return costingTypeList;
	}



	
	public List<CostingType> getAllList() {
		return allList;
	}

	public void setAllList(List<CostingType> allList) {
		this.allList = allList;
	}
	
	public CostingTypePageEvent(Page<CostingType>  costingTypePage){
		this.costingTypePage= costingTypePage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public CostingTypePageEvent(Page<CostingType>  costingTypePage, Long totalCount){
		this.costingTypePage= costingTypePage;
		this.totalCount=totalCount;
	}

	public Page<CostingType> getCostingTypePage() {
		return costingTypePage;
	}
	
	
}