package co.com.cybersoft.maintenance.tables.events.typeSorter;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.typeSorter.TypeSorterFilterInfo;

/**
 * Event class for TypeSorter
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestTypeSorterPageEvent {

	private Pageable pageable;
	private TypeSorterFilterInfo filter;
	
	public RequestTypeSorterPageEvent(Pageable pageable, TypeSorterFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestTypeSorterPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public TypeSorterFilterInfo getFilter() {
		return filter;
	}
}