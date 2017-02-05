package co.com.cybersoft.maintenance.tables.events.responsible;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.responsible.ResponsibleFilterInfo;

/**
 * Event class for Responsible
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestResponsiblePageEvent {

	private Pageable pageable;
	private ResponsibleFilterInfo filter;
	
	public RequestResponsiblePageEvent(Pageable pageable, ResponsibleFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestResponsiblePageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public ResponsibleFilterInfo getFilter() {
		return filter;
	}
}