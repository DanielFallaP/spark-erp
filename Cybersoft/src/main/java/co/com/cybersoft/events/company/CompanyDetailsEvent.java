package co.com.cybersoft.events.company;

import co.com.cybersoft.core.domain.CompanyDetails;

/**
 * Event class for Company
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CompanyDetailsEvent {
	
	private CompanyDetails companyDetails;
	
	public CompanyDetailsEvent(CompanyDetails companyDetails){
		this.companyDetails=companyDetails;
	}

	public CompanyDetails getCompanyDetails() {
		return companyDetails;
	}

}