package co.com.cybersoft.maintenance.tables.events.reference;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.reference.ReferenceFilterInfo;

/**
 * Event class for Reference
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestReferencePageEvent {

	private Pageable pageable;
	private ReferenceFilterInfo filter;
	
	public RequestReferencePageEvent(Pageable pageable, ReferenceFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestReferencePageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public ReferenceFilterInfo getFilter() {
		return filter;
	}
}