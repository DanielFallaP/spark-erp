package co.com.cybersoft.maintenance.tables.events.costingType;

import co.com.cybersoft.maintenance.tables.core.domain.CostingTypeDetails;

/**
 * Event class for CostingType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateCostingTypeEvent {
		
	private CostingTypeDetails costingTypeDetails;
	
	public CreateCostingTypeEvent(CostingTypeDetails costingTypeDetails){
		this.costingTypeDetails=costingTypeDetails;
	}

	public CostingTypeDetails getCostingTypeDetails() {
		return costingTypeDetails;
	}
	
	
}