package co.com.cybersoft.events.afe;

import org.springframework.data.domain.Pageable;

/**
 * Event class for Afe
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestAfePageEvent {

	private Pageable pageable;
	
	public RequestAfePageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}