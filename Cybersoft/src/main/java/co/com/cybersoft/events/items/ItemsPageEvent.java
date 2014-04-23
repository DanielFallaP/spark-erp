package co.com.cybersoft.events.items;

import org.springframework.data.domain.Page;

import co.com.cybersoft.persistence.domain.Items;
import co.com.cybersoft.core.domain.ItemsDetails;
import co.com.cybersoft.persistence.domain.Items;
import java.util.List;

/**
 * Event class for Items
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ItemsPageEvent {
	private Page<Items> itemsPage;
	
	private List<ItemsDetails> itemsList;



	
	public ItemsPageEvent(List<ItemsDetails>  itemsList){
			this.itemsList= itemsList;
	}



	
	public List<ItemsDetails> getItemsList() {
	return itemsList;
	}



	
	public ItemsPageEvent(Page<Items>  itemsPage){
		this.itemsPage= itemsPage;
	}

	public Page<Items> getItemsPage() {
		return itemsPage;
	}
	
}