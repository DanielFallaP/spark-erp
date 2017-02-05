package co.com.cybersoft.maintenance.tables.events.typeCauseClose;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.typeCauseClose.TypeCauseCloseFilterInfo;

/**
 * Event class for TypeCauseClose
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestTypeCauseClosePageEvent {

	private Pageable pageable;
	private TypeCauseCloseFilterInfo filter;
	
	public RequestTypeCauseClosePageEvent(Pageable pageable, TypeCauseCloseFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestTypeCauseClosePageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public TypeCauseCloseFilterInfo getFilter() {
		return filter;
	}
}