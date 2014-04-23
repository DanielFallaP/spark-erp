package co.com.cybersoft.events.contract;

import org.springframework.data.domain.Pageable;

/**
 * Event class for Contract
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestContractPageEvent {

	private Pageable pageable;
	
	public RequestContractPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}