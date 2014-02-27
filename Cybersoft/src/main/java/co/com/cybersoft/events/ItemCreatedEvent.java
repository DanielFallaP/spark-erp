package co.com.cybersoft.events;

public class ItemCreatedEvent  {
	
	private final String code;
	private final ItemDetails itemDetails;
	
	public ItemCreatedEvent(final String code, final ItemDetails itemDetails){
		this.code=code;
		this.itemDetails=itemDetails;
	}

	public ItemDetails getItemDetails() {
		return itemDetails;
	}

	public String getCode() {
		return code;
	}
	
}
