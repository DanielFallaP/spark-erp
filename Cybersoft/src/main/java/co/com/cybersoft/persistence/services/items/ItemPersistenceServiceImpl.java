package co.com.cybersoft.persistence.services.items;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.ItemDetails;
import co.com.cybersoft.events.items.CreateItemEvent;
import co.com.cybersoft.events.items.ItemCreatedEvent;
import co.com.cybersoft.events.items.ItemDetailsEvent;
import co.com.cybersoft.events.items.ItemsEvent;
import co.com.cybersoft.events.items.ModifyItemEvent;
import co.com.cybersoft.events.items.RequestItemDetailsEvent;
import co.com.cybersoft.events.items.RequestItemsEvent;
import co.com.cybersoft.persistence.domain.Item;
import co.com.cybersoft.persistence.repository.items.ItemRepository;

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
	public ItemsEvent requestItems(RequestItemsEvent event) {
		Page<Item> items = itemRepository.findAll(event.getPageable());
		return new ItemsEvent(items);
	}

	@Override
	public ItemDetailsEvent requestItemDetails(RequestItemDetailsEvent event) {
		Item item = itemRepository.findByCode(event.getId());
		ItemDetails itemDetails = item.toItemDetails();
		return new ItemDetailsEvent(itemDetails);
	}

	@Override
	public ItemDetailsEvent modifyItem(ModifyItemEvent event) {
		Item item = Item.fromItemDetails(event.getItemDetails());
		item = itemRepository.save(item);
		return new ItemDetailsEvent(item.toItemDetails());
	}

}
