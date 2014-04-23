package co.com.cybersoft.events.items;

import co.com.cybersoft.core.domain.ItemsDetails;

/**
 * Event class for Items
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ItemsModificationEvent {

	private ItemsDetails itemsDetails;
	
	public ItemsModificationEvent(ItemsDetails itemsDetails){
		this.itemsDetails=itemsDetails;
	}

	public ItemsDetails getItemsDetails() {
		return itemsDetails;
	}
	
}