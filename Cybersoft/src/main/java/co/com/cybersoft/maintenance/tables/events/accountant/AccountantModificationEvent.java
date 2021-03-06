package co.com.cybersoft.maintenance.tables.events.accountant;

import co.com.cybersoft.maintenance.tables.core.domain.AccountantDetails;

/**
 * Event class for Accountant
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class AccountantModificationEvent {

	private AccountantDetails accountantDetails;
	
	public AccountantModificationEvent(AccountantDetails accountantDetails){
		this.accountantDetails=accountantDetails;
	}

	public AccountantDetails getAccountantDetails() {
		return accountantDetails;
	}
	
}