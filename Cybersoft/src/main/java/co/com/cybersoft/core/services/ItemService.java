package co.com.cybersoft.core.services;

import co.com.cybersoft.events.ItemCreatedEvent;
import co.com.cybersoft.events.CreateItemEvent;

public interface ItemService {
	ItemCreatedEvent createItem(CreateItemEvent createItemEvent);
}
