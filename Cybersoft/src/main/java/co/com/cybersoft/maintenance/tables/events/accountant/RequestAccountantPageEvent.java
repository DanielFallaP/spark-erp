package co.com.cybersoft.maintenance.tables.events.accountant;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.accountant.AccountantFilterInfo;

/**
 * Event class for Accountant
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestAccountantPageEvent {

	private Pageable pageable;
	private AccountantFilterInfo filter;
	
	public RequestAccountantPageEvent(Pageable pageable, AccountantFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestAccountantPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public AccountantFilterInfo getFilter() {
		return filter;
	}
}