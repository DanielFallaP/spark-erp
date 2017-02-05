package co.com.cybersoft.maintenance.tables.events.typeConceptKardex;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.typeConceptKardex.TypeConceptKardexFilterInfo;

/**
 * Event class for TypeConceptKardex
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestTypeConceptKardexPageEvent {

	private Pageable pageable;
	private TypeConceptKardexFilterInfo filter;
	
	public RequestTypeConceptKardexPageEvent(Pageable pageable, TypeConceptKardexFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestTypeConceptKardexPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public TypeConceptKardexFilterInfo getFilter() {
		return filter;
	}
}