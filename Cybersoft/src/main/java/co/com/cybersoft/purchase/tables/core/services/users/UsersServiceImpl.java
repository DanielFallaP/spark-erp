package co.com.cybersoft.purchase.tables.core.services.users;

import co.com.cybersoft.purchase.tables.events.users.CreateUsersEvent;
import co.com.cybersoft.purchase.tables.events.users.UsersDetailsEvent;
import co.com.cybersoft.purchase.tables.events.users.UsersPageEvent;
import co.com.cybersoft.purchase.tables.events.users.UsersModificationEvent;
import co.com.cybersoft.purchase.tables.events.users.RequestUsersDetailsEvent;
import co.com.cybersoft.purchase.tables.events.users.RequestUsersPageEvent;
import co.com.cybersoft.purchase.tables.persistence.services.users.UsersPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class UsersServiceImpl implements UsersService{

	private final UsersPersistenceService usersPersistenceService;
	
	public UsersServiceImpl(final UsersPersistenceService usersPersistenceService){
		this.usersPersistenceService=usersPersistenceService;
	}
	
	/**
	 */
	public UsersDetailsEvent createUsers(CreateUsersEvent event ) throws Exception{
		return usersPersistenceService.createUsers(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public UsersPageEvent requestUsersPage(RequestUsersPageEvent event) throws Exception{
		return usersPersistenceService.requestUsersPage(event);
	}

	public UsersDetailsEvent requestUsersDetails(RequestUsersDetailsEvent event ) throws Exception{
		return usersPersistenceService.requestUsersDetails(event);
	}

	public UsersDetailsEvent modifyUsers(UsersModificationEvent event) throws Exception {
		return usersPersistenceService.modifyUsers(event);
	}
	
	public UsersDetailsEvent requestOnlyRecord() throws Exception {
		return usersPersistenceService.getOnlyRecord();
	}
	
	public UsersPageEvent requestUsersFilterPage(RequestUsersPageEvent event) throws Exception {
		return usersPersistenceService.requestUsersFilterPage(event);
	}
	
	public UsersPageEvent requestUsersFilter(RequestUsersPageEvent event) throws Exception {
		return usersPersistenceService.requestUsersFilter(event);
	}
	

	public UsersPageEvent requestAllByLogin(EmbeddedField... fields) throws Exception {
		return usersPersistenceService.requestAllByLogin(fields);
	}public UsersPageEvent requestAllByPassword(EmbeddedField... fields) throws Exception {
		return usersPersistenceService.requestAllByPassword(fields);
	}public UsersPageEvent requestAllByRole(EmbeddedField... fields) throws Exception {
		return usersPersistenceService.requestAllByRole(fields);
	}public UsersPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return usersPersistenceService.requestAllByCompany(fields);
	}
	
	
	
}