package co.com.cybersoft.events.items;

public class ItemDetailsEvent {
	private String id;
	private ItemDetails itemDetails;
	
	public ItemDetailsEvent(String id, ItemDetails itemDetails){
		this.id=id;
		this.itemDetails=itemDetails;
	}

	public String getId() {
		return id;
	}

	public ItemDetails getItemDetails() {
		return itemDetails;
	}
	
}
