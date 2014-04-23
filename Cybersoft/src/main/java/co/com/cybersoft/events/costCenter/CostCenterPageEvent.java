package co.com.cybersoft.events.costCenter;

import org.springframework.data.domain.Page;

import co.com.cybersoft.persistence.domain.CostCenter;
import co.com.cybersoft.core.domain.CostCenterDetails;
import co.com.cybersoft.persistence.domain.CostCenter;
import java.util.List;

/**
 * Event class for CostCenter
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CostCenterPageEvent {
	private Page<CostCenter> costCenterPage;
	
	private List<CostCenterDetails> costCenterList;



	
	public CostCenterPageEvent(List<CostCenterDetails>  costCenterList){
			this.costCenterList= costCenterList;
	}



	
	public List<CostCenterDetails> getCostCenterList() {
	return costCenterList;
	}



	
	public CostCenterPageEvent(Page<CostCenter>  costCenterPage){
		this.costCenterPage= costCenterPage;
	}

	public Page<CostCenter> getCostCenterPage() {
		return costCenterPage;
	}
	
}