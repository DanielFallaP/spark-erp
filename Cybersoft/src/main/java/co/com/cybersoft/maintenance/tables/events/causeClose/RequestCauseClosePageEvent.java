package co.com.cybersoft.maintenance.tables.events.causeClose;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.causeClose.CauseCloseFilterInfo;

/**
 * Event class for CauseClose
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestCauseClosePageEvent {

	private Pageable pageable;
	private CauseCloseFilterInfo filter;
	
	public RequestCauseClosePageEvent(Pageable pageable, CauseCloseFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestCauseClosePageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public CauseCloseFilterInfo getFilter() {
		return filter;
	}
}