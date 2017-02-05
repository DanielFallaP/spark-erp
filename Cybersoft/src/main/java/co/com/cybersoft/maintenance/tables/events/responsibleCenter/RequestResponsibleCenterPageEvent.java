package co.com.cybersoft.maintenance.tables.events.responsibleCenter;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.responsibleCenter.ResponsibleCenterFilterInfo;

/**
 * Event class for ResponsibleCenter
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestResponsibleCenterPageEvent {

	private Pageable pageable;
	private ResponsibleCenterFilterInfo filter;
	
	public RequestResponsibleCenterPageEvent(Pageable pageable, ResponsibleCenterFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestResponsibleCenterPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public ResponsibleCenterFilterInfo getFilter() {
		return filter;
	}
}