package co.com.cybersoft.events.wareHouse;

import org.springframework.data.domain.Pageable;

/**
 * Event class for WareHouse
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestWareHousePageEvent {

	private Pageable pageable;
	
	public RequestWareHousePageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}