package co.com.cybersoft.purchase.tables.events.customerTenancy;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.purchase.tables.web.domain.customerTenancy.CustomerTenancyFilterInfo;

/**
 * Event class for CustomerTenancy
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestCustomerTenancyPageEvent {

	private Pageable pageable;
	private CustomerTenancyFilterInfo filter;
	
	public RequestCustomerTenancyPageEvent(Pageable pageable, CustomerTenancyFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestCustomerTenancyPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public CustomerTenancyFilterInfo getFilter() {
		return filter;
	}
}