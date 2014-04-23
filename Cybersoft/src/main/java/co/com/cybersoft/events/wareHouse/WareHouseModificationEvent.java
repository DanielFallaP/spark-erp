package co.com.cybersoft.events.wareHouse;

import co.com.cybersoft.core.domain.WareHouseDetails;

/**
 * Event class for WareHouse
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class WareHouseModificationEvent {

	private WareHouseDetails wareHouseDetails;
	
	public WareHouseModificationEvent(WareHouseDetails wareHouseDetails){
		this.wareHouseDetails=wareHouseDetails;
	}

	public WareHouseDetails getWareHouseDetails() {
		return wareHouseDetails;
	}
	
}