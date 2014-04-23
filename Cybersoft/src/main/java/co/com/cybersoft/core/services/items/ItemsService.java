package co.com.cybersoft.core.services.items;

import co.com.cybersoft.events.items.CreateItemsEvent;
import co.com.cybersoft.events.items.ItemsDetailsEvent;
import co.com.cybersoft.events.items.ItemsPageEvent;
import co.com.cybersoft.events.items.ItemsModificationEvent;
import co.com.cybersoft.events.items.RequestItemsDetailsEvent;
import co.com.cybersoft.events.items.RequestItemsPageEvent;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface ItemsService {
	ItemsDetailsEvent createItems(CreateItemsEvent event ) throws Exception;
	
	ItemsPageEvent requestItemsPage(RequestItemsPageEvent event) throws Exception;

	ItemsDetailsEvent requestItemsDetails(RequestItemsDetailsEvent event ) throws Exception;

	ItemsDetailsEvent modifyItems(ItemsModificationEvent event) throws Exception;
	
	ItemsPageEvent requestAll() throws Exception;
	
}