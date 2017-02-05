package co.com.cybersoft.maintenance.tables.core.services.accountant;

import co.com.cybersoft.maintenance.tables.events.accountant.CreateAccountantEvent;
import co.com.cybersoft.maintenance.tables.events.accountant.AccountantDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.accountant.AccountantPageEvent;
import co.com.cybersoft.maintenance.tables.events.accountant.AccountantModificationEvent;
import co.com.cybersoft.maintenance.tables.events.accountant.RequestAccountantDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.accountant.RequestAccountantPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.accountant.AccountantPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class AccountantServiceImpl implements AccountantService{

	private final AccountantPersistenceService accountantPersistenceService;
	
	public AccountantServiceImpl(final AccountantPersistenceService accountantPersistenceService){
		this.accountantPersistenceService=accountantPersistenceService;
	}
	
	/**
	 */
	public AccountantDetailsEvent createAccountant(CreateAccountantEvent event ) throws Exception{
		return accountantPersistenceService.createAccountant(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public AccountantPageEvent requestAccountantPage(RequestAccountantPageEvent event) throws Exception{
		return accountantPersistenceService.requestAccountantPage(event);
	}

	public AccountantDetailsEvent requestAccountantDetails(RequestAccountantDetailsEvent event ) throws Exception{
		return accountantPersistenceService.requestAccountantDetails(event);
	}

	public AccountantDetailsEvent modifyAccountant(AccountantModificationEvent event) throws Exception {
		return accountantPersistenceService.modifyAccountant(event);
	}
	
	public AccountantDetailsEvent requestOnlyRecord() throws Exception {
		return accountantPersistenceService.getOnlyRecord();
	}
	
	public AccountantPageEvent requestAccountantFilterPage(RequestAccountantPageEvent event) throws Exception {
		return accountantPersistenceService.requestAccountantFilterPage(event);
	}
	
	public AccountantPageEvent requestAccountantFilter(RequestAccountantPageEvent event) throws Exception {
		return accountantPersistenceService.requestAccountantFilter(event);
	}
	

	public AccountantPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return accountantPersistenceService.requestAllByCompany(fields);
	}public AccountantPageEvent requestAllByNameAccountant(EmbeddedField... fields) throws Exception {
		return accountantPersistenceService.requestAllByNameAccountant(fields);
	}
	
	
	
}