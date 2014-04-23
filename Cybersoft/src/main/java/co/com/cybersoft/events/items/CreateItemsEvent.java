package co.com.cybersoft.events.items;

import co.com.cybersoft.core.domain.ItemsDetails;

/**
 * Event class for Items
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateItemsEvent {
		
	private ItemsDetails itemsDetails;
	
	public CreateItemsEvent(ItemsDetails itemsDetails){
		this.itemsDetails=itemsDetails;
	}

	public ItemsDetails getItemsDetails() {
		return itemsDetails;
	}
	
	
}