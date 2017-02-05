package co.com.cybersoft.maintenance.tables.events.stateCostCenters;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.stateCostCenters.StateCostCentersFilterInfo;

/**
 * Event class for StateCostCenters
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestStateCostCentersPageEvent {

	private Pageable pageable;
	private StateCostCentersFilterInfo filter;
	
	public RequestStateCostCentersPageEvent(Pageable pageable, StateCostCentersFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestStateCostCentersPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public StateCostCentersFilterInfo getFilter() {
		return filter;
	}
}