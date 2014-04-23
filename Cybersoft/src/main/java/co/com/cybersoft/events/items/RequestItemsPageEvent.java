package co.com.cybersoft.events.items;

import org.springframework.data.domain.Pageable;

/**
 * Event class for Items
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestItemsPageEvent {

	private Pageable pageable;
	
	public RequestItemsPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}