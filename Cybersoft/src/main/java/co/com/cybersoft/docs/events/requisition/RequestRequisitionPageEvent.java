package co.com.cybersoft.docs.events.requisition;

import org.springframework.data.domain.Pageable;

/**
 * Event class for Requisition
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestRequisitionPageEvent {

	private Pageable pageable;
	
	public RequestRequisitionPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}