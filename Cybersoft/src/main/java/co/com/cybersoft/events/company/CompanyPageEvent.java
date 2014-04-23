package co.com.cybersoft.events.company;

import org.springframework.data.domain.Page;

import co.com.cybersoft.persistence.domain.Company;
import co.com.cybersoft.core.domain.CompanyDetails;
import co.com.cybersoft.persistence.domain.Company;
import java.util.List;

/**
 * Event class for Company
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CompanyPageEvent {
	private Page<Company> companyPage;
	
	private List<CompanyDetails> companyList;



	
	public CompanyPageEvent(List<CompanyDetails>  companyList){
			this.companyList= companyList;
	}



	
	public List<CompanyDetails> getCompanyList() {
	return companyList;
	}



	
	public CompanyPageEvent(Page<Company>  companyPage){
		this.companyPage= companyPage;
	}

	public Page<Company> getCompanyPage() {
		return companyPage;
	}
	
}