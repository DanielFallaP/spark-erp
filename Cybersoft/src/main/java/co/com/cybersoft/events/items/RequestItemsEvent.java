package co.com.cybersoft.events.items;

import org.springframework.data.domain.Pageable;

/**
 * 
 * @author daniel
 *
 */
public class RequestItemsEvent {

	private Pageable pageable;
	
	public RequestItemsEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}
