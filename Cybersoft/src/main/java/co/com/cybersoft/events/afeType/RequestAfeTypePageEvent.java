package co.com.cybersoft.events.afeType;

import org.springframework.data.domain.Pageable;

/**
 * Event class for AfeType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestAfeTypePageEvent {

	private Pageable pageable;
	
	public RequestAfeTypePageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}