package co.com.cybersoft.purchase.tables.events.users;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.purchase.tables.web.domain.users.UsersFilterInfo;

/**
 * Event class for Users
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestUsersPageEvent {

	private Pageable pageable;
	private UsersFilterInfo filter;
	
	public RequestUsersPageEvent(Pageable pageable, UsersFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestUsersPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public UsersFilterInfo getFilter() {
		return filter;
	}
}