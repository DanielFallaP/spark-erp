package co.com.cybersoft.core.services;

import co.com.cybersoft.events.ItemCreatedEvent;
import co.com.cybersoft.events.CreateItemEvent;
import co.com.cybersoft.persistence.services.ItemPersistenceService;

public class ItemServiceImpl implements ItemService{

	private final ItemPersistenceService itemPersistenceService;
	
	public ItemServiceImpl(final ItemPersistenceService itemPersistenceService){
		this.itemPersistenceService=itemPersistenceService;
	}
	
	/**
	 */
	@Override
	public ItemCreatedEvent createItem(CreateItemEvent createItemEvent) {
		return itemPersistenceService.createItem(createItemEvent);
	}

}
