package co.com.cybersoft.events.items;

import co.com.cybersoft.core.domain.ItemDetails;

public class ItemCreatedEvent  {
	
	private final ItemDetails itemDetails;
	
	public ItemCreatedEvent(final ItemDetails itemDetails){
		this.itemDetails=itemDetails;
	}

	public ItemDetails getItemDetails() {
		return itemDetails;
	}

}
