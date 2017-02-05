package co.com.cybersoft.maintenance.tables.events.company;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.Company;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.Company;
import java.util.List;

/**
 * Event class for Company
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class CompanyPageEvent {
	private Page<Company> companyPage;
	
	private List<Company> allList;
	
	private Long totalCount;
	
	private List<CompanyDetails> companyList;



	
	public CompanyPageEvent(){
    }
	public CompanyPageEvent(List<CompanyDetails>  companyList){
			this.companyList= companyList;
	}



	
	public List<CompanyDetails> getCompanyList() {
	return companyList;
	}



	
	public List<Company> getAllList() {
		return allList;
	}

	public void setAllList(List<Company> allList) {
		this.allList = allList;
	}
	
	public CompanyPageEvent(Page<Company>  companyPage){
		this.companyPage= companyPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public CompanyPageEvent(Page<Company>  companyPage, Long totalCount){
		this.companyPage= companyPage;
		this.totalCount=totalCount;
	}

	public Page<Company> getCompanyPage() {
		return companyPage;
	}
	
	
}