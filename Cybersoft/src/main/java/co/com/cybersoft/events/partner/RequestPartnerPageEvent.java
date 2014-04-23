package co.com.cybersoft.events.partner;

import org.springframework.data.domain.Pageable;

/**
 * Event class for Partner
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestPartnerPageEvent {

	private Pageable pageable;
	
	public RequestPartnerPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}