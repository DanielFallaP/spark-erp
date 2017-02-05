package co.com.cybersoft.maintenance.tables.events.costCentersCustomers;

import co.com.cybersoft.maintenance.tables.core.domain.CostCentersCustomersDetails;

/**
 * Event class for CostCentersCustomers
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CostCentersCustomersDetailsEvent {
	
	private CostCentersCustomersDetails costCentersCustomersDetails;
	
	public CostCentersCustomersDetailsEvent(CostCentersCustomersDetails costCentersCustomersDetails){
		this.costCentersCustomersDetails=costCentersCustomersDetails;
	}

	public CostCentersCustomersDetails getCostCentersCustomersDetails() {
		return costCentersCustomersDetails;
	}

}