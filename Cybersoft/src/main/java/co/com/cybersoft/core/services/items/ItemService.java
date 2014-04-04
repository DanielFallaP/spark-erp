package co.com.cybersoft.core.services.items;

import co.com.cybersoft.events.items.CreateItemEvent;
import co.com.cybersoft.events.items.ItemCreatedEvent;
import co.com.cybersoft.events.items.ItemDetailsEvent;
import co.com.cybersoft.events.items.ItemsEvent;
import co.com.cybersoft.events.items.ModifyItemEvent;
import co.com.cybersoft.events.items.RequestItemDetailsEvent;
import co.com.cybersoft.events.items.RequestItemsEvent;

public interface ItemService {
	ItemCreatedEvent createItem(CreateItemEvent createItemEvent) throws Exception;
	
	ItemsEvent requestItems(RequestItemsEvent requestOrderDetailsEvent);

	ItemDetailsEvent requestItemDetails(RequestItemDetailsEvent requestItemDetailsEvent);

	ItemDetailsEvent modifyItem(ModifyItemEvent event);
}
