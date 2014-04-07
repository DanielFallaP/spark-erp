package co.com.cybersoft.events.items;

import co.com.cybersoft.core.domain.ItemDetails;

public class ItemDetailsEvent {
	
	private ItemDetails itemDetails;
	
	public ItemDetailsEvent(ItemDetails itemDetails){
		this.itemDetails=itemDetails;
	}

	public ItemDetails getItemDetails() {
		return itemDetails;
	}

}
