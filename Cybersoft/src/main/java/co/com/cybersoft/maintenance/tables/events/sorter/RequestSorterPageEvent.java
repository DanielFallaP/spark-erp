package co.com.cybersoft.maintenance.tables.events.sorter;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.sorter.SorterFilterInfo;

/**
 * Event class for Sorter
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestSorterPageEvent {

	private Pageable pageable;
	private SorterFilterInfo filter;
	
	public RequestSorterPageEvent(Pageable pageable, SorterFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestSorterPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public SorterFilterInfo getFilter() {
		return filter;
	}
}