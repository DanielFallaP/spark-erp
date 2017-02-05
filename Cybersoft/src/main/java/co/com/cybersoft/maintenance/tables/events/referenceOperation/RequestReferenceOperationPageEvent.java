package co.com.cybersoft.maintenance.tables.events.referenceOperation;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.referenceOperation.ReferenceOperationFilterInfo;

/**
 * Event class for ReferenceOperation
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestReferenceOperationPageEvent {

	private Pageable pageable;
	private ReferenceOperationFilterInfo filter;
	
	public RequestReferenceOperationPageEvent(Pageable pageable, ReferenceOperationFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestReferenceOperationPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public ReferenceOperationFilterInfo getFilter() {
		return filter;
	}
}