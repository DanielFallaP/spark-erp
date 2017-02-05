package co.com.cybersoft.maintenance.tables.events.company;

import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;

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