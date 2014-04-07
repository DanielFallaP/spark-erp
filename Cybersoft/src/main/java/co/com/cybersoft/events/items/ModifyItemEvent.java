package co.com.cybersoft.events.items;

import co.com.cybersoft.core.domain.ItemDetails;

public class ModifyItemEvent {

	private ItemDetails itemDetails;
	
	public ModifyItemEvent(ItemDetails itemDetails){
		this.itemDetails=itemDetails;
	}

	public ItemDetails getItemDetails() {
		return itemDetails;
	}
	
}
