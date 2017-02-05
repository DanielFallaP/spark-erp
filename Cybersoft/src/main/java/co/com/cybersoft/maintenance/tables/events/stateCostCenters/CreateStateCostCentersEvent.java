package co.com.cybersoft.maintenance.tables.events.stateCostCenters;

import co.com.cybersoft.maintenance.tables.core.domain.StateCostCentersDetails;

/**
 * Event class for StateCostCenters
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateStateCostCentersEvent {
		
	private StateCostCentersDetails stateCostCentersDetails;
	
	public CreateStateCostCentersEvent(StateCostCentersDetails stateCostCentersDetails){
		this.stateCostCentersDetails=stateCostCentersDetails;
	}

	public StateCostCentersDetails getStateCostCentersDetails() {
		return stateCostCentersDetails;
	}
	
	
}