package co.com.cybersoft.events.items;

import org.springframework.data.domain.Page;

import co.com.cybersoft.persistence.domain.Item;

public class ItemsEvent {
	private Page<Item> items;
	
	public ItemsEvent(Page<Item> items){
		this.items=items;
	}

	public Page<Item> getItems() {
		return items;
	}
	
}
