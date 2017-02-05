package co.com.cybersoft.maintenance.tables.core.services.accountant;

import co.com.cybersoft.maintenance.tables.events.accountant.CreateAccountantEvent;
import co.com.cybersoft.maintenance.tables.events.accountant.AccountantDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.accountant.AccountantPageEvent;
import co.com.cybersoft.maintenance.tables.events.accountant.AccountantModificationEvent;
import co.com.cybersoft.maintenance.tables.events.accountant.RequestAccountantDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.accountant.RequestAccountantPageEvent;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface AccountantService {
	AccountantDetailsEvent requestOnlyRecord() throws Exception;

	AccountantDetailsEvent createAccountant(CreateAccountantEvent event ) throws Exception;
	
	AccountantPageEvent requestAccountantPage(RequestAccountantPageEvent event) throws Exception;

	AccountantDetailsEvent requestAccountantDetails(RequestAccountantDetailsEvent event ) throws Exception;

	AccountantDetailsEvent modifyAccountant(AccountantModificationEvent event) throws Exception;
	
	AccountantPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	AccountantPageEvent requestAllByNameAccountant(EmbeddedField... fields) throws Exception;

	
	
	AccountantPageEvent requestAccountantFilterPage(RequestAccountantPageEvent event) throws Exception;

	AccountantPageEvent requestAccountantFilter(RequestAccountantPageEvent event) throws Exception;
	
}