package co.com.cybersoft.maintenance.tables.events.company;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.company.CompanyFilterInfo;

/**
 * Event class for Company
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestCompanyPageEvent {

	private Pageable pageable;
	private CompanyFilterInfo filter;
	
	public RequestCompanyPageEvent(Pageable pageable, CompanyFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestCompanyPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public CompanyFilterInfo getFilter() {
		return filter;
	}
}