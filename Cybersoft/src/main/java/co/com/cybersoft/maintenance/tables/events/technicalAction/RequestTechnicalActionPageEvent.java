package co.com.cybersoft.maintenance.tables.events.technicalAction;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.technicalAction.TechnicalActionFilterInfo;

/**
 * Event class for TechnicalAction
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestTechnicalActionPageEvent {

	private Pageable pageable;
	private TechnicalActionFilterInfo filter;
	
	public RequestTechnicalActionPageEvent(Pageable pageable, TechnicalActionFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestTechnicalActionPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public TechnicalActionFilterInfo getFilter() {
		return filter;
	}
}