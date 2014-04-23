package co.com.cybersoft.events.branch;

import org.springframework.data.domain.Pageable;

/**
 * Event class for Branch
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestBranchPageEvent {

	private Pageable pageable;
	
	public RequestBranchPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}