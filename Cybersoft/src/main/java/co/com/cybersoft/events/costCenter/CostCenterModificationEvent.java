package co.com.cybersoft.events.costCenter;

import co.com.cybersoft.core.domain.CostCenterDetails;

/**
 * Event class for CostCenter
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CostCenterModificationEvent {

	private CostCenterDetails costCenterDetails;
	
	public CostCenterModificationEvent(CostCenterDetails costCenterDetails){
		this.costCenterDetails=costCenterDetails;
	}

	public CostCenterDetails getCostCenterDetails() {
		return costCenterDetails;
	}
	
}