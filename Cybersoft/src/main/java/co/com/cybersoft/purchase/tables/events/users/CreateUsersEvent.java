package co.com.cybersoft.purchase.tables.events.users;

import co.com.cybersoft.purchase.tables.core.domain.UsersDetails;

/**
 * Event class for Users
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateUsersEvent {
		
	private UsersDetails usersDetails;
	
	public CreateUsersEvent(UsersDetails usersDetails){
		this.usersDetails=usersDetails;
	}

	public UsersDetails getUsersDetails() {
		return usersDetails;
	}
	
	
}