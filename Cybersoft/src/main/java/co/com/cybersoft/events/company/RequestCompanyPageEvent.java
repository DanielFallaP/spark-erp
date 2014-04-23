package co.com.cybersoft.events.company;

import org.springframework.data.domain.Pageable;

/**
 * Event class for Company
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestCompanyPageEvent {

	private Pageable pageable;
	
	public RequestCompanyPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}