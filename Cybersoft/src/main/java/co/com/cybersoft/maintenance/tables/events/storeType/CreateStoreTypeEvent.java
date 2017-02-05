package co.com.cybersoft.maintenance.tables.events.storeType;

import co.com.cybersoft.maintenance.tables.core.domain.StoreTypeDetails;

/**
 * Event class for StoreType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateStoreTypeEvent {
		
	private StoreTypeDetails storeTypeDetails;
	
	public CreateStoreTypeEvent(StoreTypeDetails storeTypeDetails){
		this.storeTypeDetails=storeTypeDetails;
	}

	public StoreTypeDetails getStoreTypeDetails() {
		return storeTypeDetails;
	}
	
	
}