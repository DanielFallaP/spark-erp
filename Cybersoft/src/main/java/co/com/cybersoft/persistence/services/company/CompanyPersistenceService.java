package co.com.cybersoft.persistence.services.company;

import co.com.cybersoft.events.company.CreateCompanyEvent;
import co.com.cybersoft.events.company.CompanyDetailsEvent;
import co.com.cybersoft.events.company.CompanyPageEvent;
import co.com.cybersoft.events.company.CompanyModificationEvent;
import co.com.cybersoft.events.company.RequestCompanyDetailsEvent;
import co.com.cybersoft.events.company.RequestCompanyPageEvent;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface CompanyPersistenceService {

	CompanyDetailsEvent createCompany(CreateCompanyEvent event) throws Exception;

	CompanyPageEvent requestCompanyPage(RequestCompanyPageEvent event) throws Exception;

	CompanyDetailsEvent requestCompanyDetails(RequestCompanyDetailsEvent event) throws Exception;
	
	CompanyDetailsEvent modifyCompany(CompanyModificationEvent event) throws Exception;
	
	CompanyPageEvent requestAll() throws Exception;
	
}