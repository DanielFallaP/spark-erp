package co.com.cybersoft.core.services.company;

import co.com.cybersoft.events.company.CreateCompanyEvent;
import co.com.cybersoft.events.company.CompanyDetailsEvent;
import co.com.cybersoft.events.company.CompanyPageEvent;
import co.com.cybersoft.events.company.CompanyModificationEvent;
import co.com.cybersoft.events.company.RequestCompanyDetailsEvent;
import co.com.cybersoft.events.company.RequestCompanyPageEvent;
import co.com.cybersoft.persistence.services.company.CompanyPersistenceService;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CompanyServiceImpl implements CompanyService{

	private final CompanyPersistenceService companyPersistenceService;
	
	public CompanyServiceImpl(final CompanyPersistenceService companyPersistenceService){
		this.companyPersistenceService=companyPersistenceService;
	}
	
	/**
	 */
	@Override
	public CompanyDetailsEvent createCompany(CreateCompanyEvent event ) throws Exception{
		return companyPersistenceService.createCompany(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	@Override
	public CompanyPageEvent requestCompanyPage(RequestCompanyPageEvent event) throws Exception{
		return companyPersistenceService.requestCompanyPage(event);
	}

	@Override
	public CompanyDetailsEvent requestCompanyDetails(RequestCompanyDetailsEvent event ) throws Exception{
		return companyPersistenceService.requestCompanyDetails(event);
	}

	@Override
	public CompanyDetailsEvent modifyCompany(CompanyModificationEvent event) throws Exception {
		return companyPersistenceService.modifyCompany(event);
	}
	
	@Override
	public CompanyPageEvent requestAll() throws Exception {
		return companyPersistenceService.requestAll();
	}
	
	@Override
	public CompanyPageEvent requestByCodePrefix(String codePrefix) throws Exception {
		return companyPersistenceService.requestByCodePrefix(codePrefix);
	}

	@Override
	public CompanyPageEvent requestByContainingDescription(String description) throws Exception {
		return companyPersistenceService.requestByContainingDescription(description);
	}
	
}