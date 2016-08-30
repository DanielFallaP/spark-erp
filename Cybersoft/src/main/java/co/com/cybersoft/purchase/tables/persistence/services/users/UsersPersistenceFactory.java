package co.com.cybersoft.purchase.tables.persistence.services.users;

import co.com.cybersoft.purchase.tables.persistence.repository.users.UsersRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class UsersPersistenceFactory {

	UsersRepository usersRepository;
	
	public UsersPersistenceFactory(UsersRepository usersRepository){
		this.usersRepository=usersRepository;
	}
	
	public co.com.cybersoft.purchase.tables.persistence.domain.Users getUsersByField(String field, String value){
		if (field.equals("login"))
					return usersRepository.findByLogin(value);if (field.equals("password"))
					return usersRepository.findByPassword(value);if (field.equals("role"))
					return usersRepository.findByRole(value);		
		return null;
	}
}