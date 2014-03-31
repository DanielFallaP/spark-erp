package co.com.cybersoft.events.items;

import org.springframework.data.domain.Pageable;

/**
 * 
 * @author daniel
 *
 */
public class RequestItemDetailsEvent {

	private Pageable pageable;
	
	public RequestItemDetailsEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}
