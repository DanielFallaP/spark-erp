package co.com.cybersoft.maintenance.tables.events.failureCause;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.failureCause.FailureCauseFilterInfo;

/**
 * Event class for FailureCause
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestFailureCausePageEvent {

	private Pageable pageable;
	private FailureCauseFilterInfo filter;
	
	public RequestFailureCausePageEvent(Pageable pageable, FailureCauseFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestFailureCausePageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public FailureCauseFilterInfo getFilter() {
		return filter;
	}
}