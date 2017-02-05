package co.com.cybersoft.maintenance.tables.events.causePending;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.causePending.CausePendingFilterInfo;

/**
 * Event class for CausePending
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestCausePendingPageEvent {

	private Pageable pageable;
	private CausePendingFilterInfo filter;
	
	public RequestCausePendingPageEvent(Pageable pageable, CausePendingFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestCausePendingPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public CausePendingFilterInfo getFilter() {
		return filter;
	}
}