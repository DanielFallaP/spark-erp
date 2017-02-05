package co.com.cybersoft.maintenance.tables.events.costCentersCustomers;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.CostCentersCustomers;
import co.com.cybersoft.maintenance.tables.core.domain.CostCentersCustomersDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.CostCentersCustomers;
import java.util.List;

/**
 * Event class for CostCentersCustomers
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class CostCentersCustomersPageEvent {
	private Page<CostCentersCustomers> costCentersCustomersPage;
	
	private List<CostCentersCustomers> allList;
	
	private Long totalCount;
	
	private List<CostCentersCustomersDetails> costCentersCustomersList;



	
	public CostCentersCustomersPageEvent(){
    }
	public CostCentersCustomersPageEvent(List<CostCentersCustomersDetails>  costCentersCustomersList){
			this.costCentersCustomersList= costCentersCustomersList;
	}



	
	public List<CostCentersCustomersDetails> getCostCentersCustomersList() {
	return costCentersCustomersList;
	}



	
	public List<CostCentersCustomers> getAllList() {
		return allList;
	}

	public void setAllList(List<CostCentersCustomers> allList) {
		this.allList = allList;
	}
	
	public CostCentersCustomersPageEvent(Page<CostCentersCustomers>  costCentersCustomersPage){
		this.costCentersCustomersPage= costCentersCustomersPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public CostCentersCustomersPageEvent(Page<CostCentersCustomers>  costCentersCustomersPage, Long totalCount){
		this.costCentersCustomersPage= costCentersCustomersPage;
		this.totalCount=totalCount;
	}

	public Page<CostCentersCustomers> getCostCentersCustomersPage() {
		return costCentersCustomersPage;
	}
	
	
}