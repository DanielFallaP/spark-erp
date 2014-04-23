package co.com.cybersoft.events.corporation;

import org.springframework.data.domain.Pageable;

/**
 * Event class for Corporation
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestCorporationPageEvent {

	private Pageable pageable;
	
	public RequestCorporationPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}