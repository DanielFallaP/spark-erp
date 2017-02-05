package co.com.cybersoft.maintenance.tables.events.physicalLocation;

import co.com.cybersoft.maintenance.tables.core.domain.PhysicalLocationDetails;

/**
 * Event class for PhysicalLocation
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class PhysicalLocationDetailsEvent {
	
	private PhysicalLocationDetails physicalLocationDetails;
	
	public PhysicalLocationDetailsEvent(PhysicalLocationDetails physicalLocationDetails){
		this.physicalLocationDetails=physicalLocationDetails;
	}

	public PhysicalLocationDetails getPhysicalLocationDetails() {
		return physicalLocationDetails;
	}

}