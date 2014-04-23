package co.com.cybersoft.events.active;

import org.springframework.data.domain.Pageable;

/**
 * Event class for Active
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestActivePageEvent {

	private Pageable pageable;
	
	public RequestActivePageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}