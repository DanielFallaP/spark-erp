package co.com.cybersoft.maintenance.tables.events.third;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.third.ThirdFilterInfo;

/**
 * Event class for Third
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestThirdPageEvent {

	private Pageable pageable;
	private ThirdFilterInfo filter;
	
	public RequestThirdPageEvent(Pageable pageable, ThirdFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestThirdPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public ThirdFilterInfo getFilter() {
		return filter;
	}
}