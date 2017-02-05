package co.com.cybersoft.maintenance.tables.persistence.services.accountant;

import co.com.cybersoft.maintenance.tables.persistence.repository.accountant.AccountantRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class AccountantPersistenceFactory {

	AccountantRepository accountantRepository;
	
	public AccountantPersistenceFactory(AccountantRepository accountantRepository){
		this.accountantRepository=accountantRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.Accountant getAccountantByField(String field, String value){
		if (field.equals("nameAccountant"))
					return accountantRepository.findByNameAccountant(value);		
		return null;
	}
}