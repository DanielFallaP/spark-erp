package co.com.cybersoft.events.costCenter;

import co.com.cybersoft.core.domain.CostCenterDetails;

/**
 * Event class for CostCenter
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateCostCenterEvent {
		
	private CostCenterDetails costCenterDetails;
	
	public CreateCostCenterEvent(CostCenterDetails costCenterDetails){
		this.costCenterDetails=costCenterDetails;
	}

	public CostCenterDetails getCostCenterDetails() {
		return costCenterDetails;
	}
	
	
}