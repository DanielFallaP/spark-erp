package co.com.cybersoft.purchase.tables.persistence.services.users;

import co.com.cybersoft.purchase.tables.events.users.CreateUsersEvent;
import co.com.cybersoft.purchase.tables.events.users.UsersDetailsEvent;
import co.com.cybersoft.purchase.tables.events.users.UsersPageEvent;
import co.com.cybersoft.purchase.tables.events.users.UsersModificationEvent;
import co.com.cybersoft.purchase.tables.events.users.RequestUsersDetailsEvent;
import co.com.cybersoft.purchase.tables.events.users.RequestUsersPageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface UsersPersistenceService {

	UsersDetailsEvent createUsers(CreateUsersEvent event) throws Exception;

	UsersPageEvent requestUsersPage(RequestUsersPageEvent event) throws Exception;

	UsersDetailsEvent requestUsersDetails(RequestUsersDetailsEvent event) throws Exception;
	
	UsersDetailsEvent modifyUsers(UsersModificationEvent event) throws Exception;
	UsersPageEvent requestUsersFilterPage(RequestUsersPageEvent event) throws Exception;
	UsersPageEvent requestUsersFilter(RequestUsersPageEvent event) throws Exception;
	
	UsersPageEvent requestAllByLogin(EmbeddedField... fields) throws Exception;
	UsersPageEvent requestAllByPassword(EmbeddedField... fields) throws Exception;
	UsersPageEvent requestAllByRole(EmbeddedField... fields) throws Exception;
	UsersPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;

	
	
	UsersDetailsEvent getOnlyRecord() throws Exception;
	
}