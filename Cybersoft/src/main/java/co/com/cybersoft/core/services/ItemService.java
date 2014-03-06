package co.com.cybersoft.core.services;

import co.com.cybersoft.events.items.CreateItemEvent;
import co.com.cybersoft.events.items.ItemCreatedEvent;
import co.com.cybersoft.events.items.ItemDetailsEvent;
import co.com.cybersoft.events.items.RequestItemDetailsEvent;

public interface ItemService {
	ItemCreatedEvent createItem(CreateItemEvent createItemEvent);
	
	ItemDetailsEvent requestItemDetails(RequestItemDetailsEvent requestOrderDetailsEvent);
}
