package co.com.cybersoft.events.company;

import co.com.cybersoft.core.domain.CompanyDetails;

/**
 * Event class for Company
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CompanyModificationEvent {

	private CompanyDetails companyDetails;
	
	public CompanyModificationEvent(CompanyDetails companyDetails){
		this.companyDetails=companyDetails;
	}

	public CompanyDetails getCompanyDetails() {
		return companyDetails;
	}
	
}