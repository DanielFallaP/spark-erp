package co.com.cybersoft.maintenance.tables.events.operation;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.operation.OperationFilterInfo;

/**
 * Event class for Operation
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestOperationPageEvent {

	private Pageable pageable;
	private OperationFilterInfo filter;
	
	public RequestOperationPageEvent(Pageable pageable, OperationFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestOperationPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public OperationFilterInfo getFilter() {
		return filter;
	}
}