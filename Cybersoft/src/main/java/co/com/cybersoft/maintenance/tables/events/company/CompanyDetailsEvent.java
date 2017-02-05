package co.com.cybersoft.maintenance.tables.events.company;

import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;

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