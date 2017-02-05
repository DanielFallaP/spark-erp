package co.com.cybersoft.maintenance.tables.events.costingType;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.costingType.CostingTypeFilterInfo;

/**
 * Event class for CostingType
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestCostingTypePageEvent {

	private Pageable pageable;
	private CostingTypeFilterInfo filter;
	
	public RequestCostingTypePageEvent(Pageable pageable, CostingTypeFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestCostingTypePageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public CostingTypeFilterInfo getFilter() {
		return filter;
	}
}