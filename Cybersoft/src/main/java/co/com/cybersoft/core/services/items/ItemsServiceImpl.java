package co.com.cybersoft.core.services.items;

import co.com.cybersoft.events.items.CreateItemsEvent;
import co.com.cybersoft.events.items.ItemsDetailsEvent;
import co.com.cybersoft.events.items.ItemsPageEvent;
import co.com.cybersoft.events.items.ItemsModificationEvent;
import co.com.cybersoft.events.items.RequestItemsDetailsEvent;
import co.com.cybersoft.events.items.RequestItemsPageEvent;
import co.com.cybersoft.persistence.services.items.ItemsPersistenceService;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ItemsServiceImpl implements ItemsService{

	private final ItemsPersistenceService itemsPersistenceService;
	
	public ItemsServiceImpl(final ItemsPersistenceService itemsPersistenceService){
		this.itemsPersistenceService=itemsPersistenceService;
	}
	
	/**
	 */
	@Override
	public ItemsDetailsEvent createItems(CreateItemsEvent event ) throws Exception{
		return itemsPersistenceService.createItems(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	@Override
	public ItemsPageEvent requestItemsPage(RequestItemsPageEvent event) throws Exception{
		return itemsPersistenceService.requestItemsPage(event);
	}

	@Override
	public ItemsDetailsEvent requestItemsDetails(RequestItemsDetailsEvent event ) throws Exception{
		return itemsPersistenceService.requestItemsDetails(event);
	}

	@Override
	public ItemsDetailsEvent modifyItems(ItemsModificationEvent event) throws Exception {
		return itemsPersistenceService.modifyItems(event);
	}
	
	@Override
	public ItemsPageEvent requestAll() throws Exception {
		return itemsPersistenceService.requestAll();
	}
	
	@Override
	public ItemsPageEvent requestByCodePrefix(String codePrefix) throws Exception {
		return itemsPersistenceService.requestByCodePrefix(codePrefix);
	}

	@Override
	public ItemsPageEvent requestByContainingDescription(String description) throws Exception {
		return itemsPersistenceService.requestByContainingDescription(description);
	}
	
}