package co.com.cybersoft.events.costCenter;

import org.springframework.data.domain.Pageable;

/**
 * Event class for CostCenter
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestCostCenterPageEvent {

	private Pageable pageable;
	
	public RequestCostCenterPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}