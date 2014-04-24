package co.com.cybersoft.persistence.services.items;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.ItemsDetails;
import co.com.cybersoft.events.items.CreateItemsEvent;
import co.com.cybersoft.events.items.ItemsDetailsEvent;
import co.com.cybersoft.events.items.ItemsPageEvent;
import co.com.cybersoft.events.items.ItemsModificationEvent;
import co.com.cybersoft.events.items.RequestItemsDetailsEvent;
import co.com.cybersoft.events.items.RequestItemsPageEvent;
import co.com.cybersoft.persistence.domain.Items;
import co.com.cybersoft.persistence.repository.items.ItemsRepository;
import co.com.cybersoft.persistence.repository.items.ItemsCustomRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ItemsPersistenceServiceImpl implements ItemsPersistenceService{

	private final ItemsRepository itemsRepository;
	
	private final ItemsCustomRepo itemsCustomRepo;
	
	public ItemsPersistenceServiceImpl(final ItemsRepository itemsRepository, final ItemsCustomRepo itemsCustomRepo) {
		this.itemsRepository=itemsRepository;
		this.itemsCustomRepo=itemsCustomRepo;
	}
	
	@Override
	public ItemsDetailsEvent createItems(CreateItemsEvent event) throws Exception{
		Items items = Items.fromItemsDetails(event.getItemsDetails());
		items = itemsRepository.save(items);
		return new ItemsDetailsEvent(items.toItemsDetails());
	}

	@Override
	public ItemsPageEvent requestItemsPage(RequestItemsPageEvent event) throws Exception {
		Page<Items> itemss = itemsRepository.findAll(event.getPageable());
		return new ItemsPageEvent(itemss);
	}

	@Override
	public ItemsDetailsEvent requestItemsDetails(RequestItemsDetailsEvent event) throws Exception {
		ItemsDetails itemsDetails=null;
		if (event.getId()!=null){
			Items items = itemsRepository.findByCode(event.getId());
			if (items!=null)
				itemsDetails = items.toItemsDetails();
		}
		else{
					Items items = itemsRepository.findByDescription(event.getDescription());
					if (items!=null)
						itemsDetails = items.toItemsDetails();
				}
		return new ItemsDetailsEvent(itemsDetails);
		
	}

	@Override
	public ItemsDetailsEvent modifyItems(ItemsModificationEvent event) throws Exception {
		Items items = Items.fromItemsDetails(event.getItemsDetails());
		items = itemsRepository.save(items);
		return new ItemsDetailsEvent(items.toItemsDetails());
	}
	
	@Override
	public ItemsPageEvent requestAll() throws Exception {
		List<Items> all = itemsRepository.findAll();
		List<ItemsDetails> list = new ArrayList<ItemsDetails>();
		for (Items items : all) {
			list.add(items.toItemsDetails());
		}
		return new ItemsPageEvent(list);
	}
	
	@Override
	public ItemsPageEvent requestByCodePrefix(String codePrefix) throws Exception {
		List<Items> codes = itemsCustomRepo.findByStartingCodeNumber(codePrefix);
		ArrayList<ItemsDetails> list = new ArrayList<ItemsDetails>();
		for (Items items : codes) {
			list.add(items.toItemsDetails());
		}
		return new ItemsPageEvent(list);
	}

	@Override
	public ItemsPageEvent requestByContainingDescription(String description) throws Exception {
		ArrayList<ItemsDetails> list = new ArrayList<ItemsDetails>();
		List<Items> descriptions = itemsCustomRepo.findByContainingDescription(description);
		for (Items items : descriptions) {
			list.add(items.toItemsDetails());
		}
		return new ItemsPageEvent(list);
	}

}