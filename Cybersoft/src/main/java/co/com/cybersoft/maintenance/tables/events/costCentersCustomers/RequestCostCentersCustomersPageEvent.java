package co.com.cybersoft.maintenance.tables.events.costCentersCustomers;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.costCentersCustomers.CostCentersCustomersFilterInfo;

/**
 * Event class for CostCentersCustomers
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestCostCentersCustomersPageEvent {

	private Pageable pageable;
	private CostCentersCustomersFilterInfo filter;
	
	public RequestCostCentersCustomersPageEvent(Pageable pageable, CostCentersCustomersFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestCostCentersCustomersPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public CostCentersCustomersFilterInfo getFilter() {
		return filter;
	}
}