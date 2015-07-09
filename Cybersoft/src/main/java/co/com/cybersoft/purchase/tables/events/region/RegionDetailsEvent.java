package co.com.cybersoft.purchase.tables.events.region;

import co.com.cybersoft.purchase.tables.core.domain.RegionDetails;

/**
 * Event class for Region
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RegionDetailsEvent {
	
	private RegionDetails regionDetails;
	
	public RegionDetailsEvent(RegionDetails regionDetails){
		this.regionDetails=regionDetails;
	}

	public RegionDetails getRegionDetails() {
		return regionDetails;
	}

}