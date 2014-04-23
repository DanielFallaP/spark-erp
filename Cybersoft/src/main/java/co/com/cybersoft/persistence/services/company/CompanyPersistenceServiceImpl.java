package co.com.cybersoft.persistence.services.company;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.CompanyDetails;
import co.com.cybersoft.events.company.CreateCompanyEvent;
import co.com.cybersoft.events.company.CompanyDetailsEvent;
import co.com.cybersoft.events.company.CompanyPageEvent;
import co.com.cybersoft.events.company.CompanyModificationEvent;
import co.com.cybersoft.events.company.RequestCompanyDetailsEvent;
import co.com.cybersoft.events.company.RequestCompanyPageEvent;
import co.com.cybersoft.persistence.domain.Company;
import co.com.cybersoft.persistence.repository.CompanyRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CompanyPersistenceServiceImpl implements CompanyPersistenceService{

	private final CompanyRepository companyRepository;
	
	public CompanyPersistenceServiceImpl(final CompanyRepository companyRepository) {
		this.companyRepository=companyRepository;
	}
	
	@Override
	public CompanyDetailsEvent createCompany(CreateCompanyEvent event) throws Exception{
		Company company = Company.fromCompanyDetails(event.getCompanyDetails());
		company = companyRepository.save(company);
		return new CompanyDetailsEvent(company.toCompanyDetails());
	}

	@Override
	public CompanyPageEvent requestCompanyPage(RequestCompanyPageEvent event) throws Exception {
		Page<Company> companys = companyRepository.findAll(event.getPageable());
		return new CompanyPageEvent(companys);
	}

	@Override
	public CompanyDetailsEvent requestCompanyDetails(RequestCompanyDetailsEvent event) throws Exception {
		Company company = companyRepository.findByCode(event.getId());
		CompanyDetails companyDetails = company.toCompanyDetails();
		return new CompanyDetailsEvent(companyDetails);
	}

	@Override
	public CompanyDetailsEvent modifyCompany(CompanyModificationEvent event) throws Exception {
		Company company = Company.fromCompanyDetails(event.getCompanyDetails());
		company = companyRepository.save(company);
		return new CompanyDetailsEvent(company.toCompanyDetails());
	}
	
	@Override
	public CompanyPageEvent requestAll() throws Exception {
		List<Company> all = companyRepository.findAll();
		List<CompanyDetails> list = new ArrayList<CompanyDetails>();
		for (Company company : all) {
			list.add(company.toCompanyDetails());
		}
		return new CompanyPageEvent(list);
	}

}