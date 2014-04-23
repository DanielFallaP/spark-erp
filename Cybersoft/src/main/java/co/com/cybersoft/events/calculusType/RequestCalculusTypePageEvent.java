package co.com.cybersoft.events.calculusType;

import org.springframework.data.domain.Pageable;

/**
 * Event class for CalculusType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestCalculusTypePageEvent {

	private Pageable pageable;
	
	public RequestCalculusTypePageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}