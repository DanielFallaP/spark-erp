package co.com.cybersoft.persistence.services;

import co.com.cybersoft.events.ItemCreatedEvent;
import co.com.cybersoft.events.CreateItemEvent;
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

}
