package co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.failureCauseTechnicalaction.FailureCauseTechnicalactionFilterInfo;

/**
 * Event class for FailureCauseTechnicalaction
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestFailureCauseTechnicalactionPageEvent {

	private Pageable pageable;
	private FailureCauseTechnicalactionFilterInfo filter;
	
	public RequestFailureCauseTechnicalactionPageEvent(Pageable pageable, FailureCauseTechnicalactionFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestFailureCauseTechnicalactionPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public FailureCauseTechnicalactionFilterInfo getFilter() {
		return filter;
	}
}