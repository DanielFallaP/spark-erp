package co.com.cybersoft.events.items;

public class ItemDetailsEvent {
	
	private ItemDetails itemDetails;
	
	public ItemDetailsEvent(ItemDetails itemDetails){
		this.itemDetails=itemDetails;
	}

	public ItemDetails getItemDetails() {
		return itemDetails;
	}

}
