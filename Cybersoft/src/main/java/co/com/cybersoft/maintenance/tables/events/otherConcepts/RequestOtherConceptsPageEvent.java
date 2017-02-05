package co.com.cybersoft.maintenance.tables.events.otherConcepts;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.otherConcepts.OtherConceptsFilterInfo;

/**
 * Event class for OtherConcepts
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestOtherConceptsPageEvent {

	private Pageable pageable;
	private OtherConceptsFilterInfo filter;
	
	public RequestOtherConceptsPageEvent(Pageable pageable, OtherConceptsFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestOtherConceptsPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public OtherConceptsFilterInfo getFilter() {
		return filter;
	}
}