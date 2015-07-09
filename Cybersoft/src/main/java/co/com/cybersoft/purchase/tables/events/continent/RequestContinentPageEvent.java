package co.com.cybersoft.purchase.tables.events.continent;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.purchase.tables.web.domain.continent.ContinentFilterInfo;

/**
 * Event class for Continent
 * @author Cybersystems 2015. All rights reserved.
 *
 */
public class RequestContinentPageEvent {

	private Pageable pageable;
	private ContinentFilterInfo filter;
	
	public RequestContinentPageEvent(Pageable pageable, ContinentFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestContinentPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public ContinentFilterInfo getFilter() {
		return filter;
	}
}