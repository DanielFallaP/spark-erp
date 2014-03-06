package co.com.cybersoft.persistence.services;

import co.com.cybersoft.events.items.CreateItemEvent;
import co.com.cybersoft.events.items.ItemCreatedEvent;
import co.com.cybersoft.events.items.ItemDetailsEvent;
import co.com.cybersoft.events.items.RequestItemDetailsEvent;

public interface ItemPersistenceService {

	public ItemCreatedEvent createItem(CreateItemEvent event);

	public ItemDetailsEvent requestItemDetails(RequestItemDetailsEvent event);
}
