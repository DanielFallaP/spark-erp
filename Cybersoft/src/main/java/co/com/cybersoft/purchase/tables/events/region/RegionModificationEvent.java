package co.com.cybersoft.purchase.tables.events.region;

import co.com.cybersoft.purchase.tables.core.domain.RegionDetails;

/**
 * Event class for Region
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RegionModificationEvent {

	private RegionDetails regionDetails;
	
	public RegionModificationEvent(RegionDetails regionDetails){
		this.regionDetails=regionDetails;
	}

	public RegionDetails getRegionDetails() {
		return regionDetails;
	}
	
}