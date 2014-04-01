package co.com.cybersoft.persistence.services;

import org.springframework.data.domain.Page;

import co.com.cybersoft.events.items.CreateItemEvent;
import co.com.cybersoft.events.items.ItemCreatedEvent;
import co.com.cybersoft.events.items.ItemDetailsEvent;
import co.com.cybersoft.events.items.RequestItemDetailsEvent;
import co.com.cybersoft.persistence.domain.Item;
import co.com.cybersoft.persistence.repository.ItemRepository;

public class ItemPersistenceServiceImpl implements ItemPersistenceService{

	private final ItemRepository itemRepository;
	
	public ItemPersistenceServiceImpl(final ItemRepository itemRepository){
		this.itemRepository=itemRepository;
	}
	
	@Override
	public ItemCreatedEvent createItem(CreateItemEvent event) {
		Item item = Item.fromItemDetails(event.getDetails());
		item = itemRepository.save(item);
		return new ItemCreatedEvent(item.getId(), item.toItemDetails());
	}

	@Override
	public ItemDetailsEvent requestItemDetails(RequestItemDetailsEvent event) {
		Page<Item> items = itemRepository.findAll(event.getPageable());
		return new ItemDetailsEvent(items);
	}

}
