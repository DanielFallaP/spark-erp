package co.com.cybersoft.events.items;

import co.com.cybersoft.core.domain.ItemDetails;


public class CreateItemEvent {

	private ItemDetails details;
	
	public CreateItemEvent(ItemDetails details){
		this.details=details;
	}
	
	public ItemDetails getDetails(){
		return details;
	}
}
