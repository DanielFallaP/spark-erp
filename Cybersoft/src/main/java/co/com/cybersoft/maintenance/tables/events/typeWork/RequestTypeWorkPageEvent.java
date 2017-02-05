package co.com.cybersoft.maintenance.tables.events.typeWork;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.typeWork.TypeWorkFilterInfo;

/**
 * Event class for TypeWork
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestTypeWorkPageEvent {

	private Pageable pageable;
	private TypeWorkFilterInfo filter;
	
	public RequestTypeWorkPageEvent(Pageable pageable, TypeWorkFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestTypeWorkPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public TypeWorkFilterInfo getFilter() {
		return filter;
	}
}