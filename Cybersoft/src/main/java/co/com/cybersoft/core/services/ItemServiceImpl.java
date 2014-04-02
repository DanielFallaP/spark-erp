package co.com.cybersoft.core.services;

import java.awt.event.ItemEvent;
import java.util.Date;

import co.com.cybersoft.events.items.CreateItemEvent;
import co.com.cybersoft.events.items.ItemCreatedEvent;
import co.com.cybersoft.events.items.ItemDetailsEvent;
import co.com.cybersoft.events.items.ItemsEvent;
import co.com.cybersoft.events.items.ModifyItemEvent;
import co.com.cybersoft.events.items.RequestItemDetailsEvent;
import co.com.cybersoft.events.items.RequestItemsEvent;
import co.com.cybersoft.persistence.services.items.ItemPersistenceService;

public class ItemServiceImpl implements ItemService{

	private final ItemPersistenceService itemPersistenceService;
	
	public ItemServiceImpl(final ItemPersistenceService itemPersistenceService){
		this.itemPersistenceService=itemPersistenceService;
	}
	
	/**
	 */
	@Override
	public ItemCreatedEvent createItem(CreateItemEvent createItemEvent) {
		createItemEvent.getDetails().setDateOfCreation(new Date());
		ItemCreatedEvent createItem = itemPersistenceService.createItem(createItemEvent);
		return createItem;
	}

	/**
	 * Returns a list of items that satisfy the criteria in the request
	 */
	@Override
	public ItemsEvent requestItems(
			RequestItemsEvent requestItemDetailsEvent) {
		return itemPersistenceService.requestItems(requestItemDetailsEvent);
	}

	@Override
	public ItemDetailsEvent requestItemDetails(
			RequestItemDetailsEvent requestItemDetailsEvent) {
		return itemPersistenceService.requestItemDetails(requestItemDetailsEvent);
	}

	@Override
	public ItemDetailsEvent modifyItem(ModifyItemEvent event) {
		return itemPersistenceService.modifyItem(event);
	}

}
