package co.com.cybersoft.maintenance.tables.events.state;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.state.StateFilterInfo;

/**
 * Event class for State
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestStatePageEvent {

	private Pageable pageable;
	private StateFilterInfo filter;
	
	public RequestStatePageEvent(Pageable pageable, StateFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestStatePageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public StateFilterInfo getFilter() {
		return filter;
	}
}