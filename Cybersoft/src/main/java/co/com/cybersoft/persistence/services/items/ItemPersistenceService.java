package co.com.cybersoft.persistence.services.items;

import co.com.cybersoft.events.items.CreateItemEvent;
import co.com.cybersoft.events.items.ItemCreatedEvent;
import co.com.cybersoft.events.items.ItemDetailsEvent;
import co.com.cybersoft.events.items.ItemsEvent;
import co.com.cybersoft.events.items.ModifyItemEvent;
import co.com.cybersoft.events.items.RequestItemDetailsEvent;
import co.com.cybersoft.events.items.RequestItemsEvent;

public interface ItemPersistenceService {

	ItemCreatedEvent createItem(CreateItemEvent event);

	ItemsEvent requestItems(RequestItemsEvent event);

	ItemDetailsEvent requestItemDetails(RequestItemDetailsEvent event);
	
	ItemDetailsEvent modifyItem(ModifyItemEvent event);
}
