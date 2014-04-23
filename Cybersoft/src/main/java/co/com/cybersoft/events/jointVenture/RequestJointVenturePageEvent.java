package co.com.cybersoft.events.jointVenture;

import org.springframework.data.domain.Pageable;

/**
 * Event class for JointVenture
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestJointVenturePageEvent {

	private Pageable pageable;
	
	public RequestJointVenturePageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}