package co.com.cybersoft.maintenance.tables.events.conceptKardex;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.conceptKardex.ConceptKardexFilterInfo;

/**
 * Event class for ConceptKardex
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestConceptKardexPageEvent {

	private Pageable pageable;
	private ConceptKardexFilterInfo filter;
	
	public RequestConceptKardexPageEvent(Pageable pageable, ConceptKardexFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestConceptKardexPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public ConceptKardexFilterInfo getFilter() {
		return filter;
	}
}