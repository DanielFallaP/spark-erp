package co.com.cybersoft.events.operationType;

import org.springframework.data.domain.Pageable;

/**
 * Event class for OperationType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestOperationTypePageEvent {

	private Pageable pageable;
	
	public RequestOperationTypePageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}