package co.com.cybersoft.persistence.services;

import co.com.cybersoft.events.ItemCreatedEvent;
import co.com.cybersoft.events.CreateItemEvent;

public interface ItemPersistenceService {

	public ItemCreatedEvent createItem(CreateItemEvent event);
}
