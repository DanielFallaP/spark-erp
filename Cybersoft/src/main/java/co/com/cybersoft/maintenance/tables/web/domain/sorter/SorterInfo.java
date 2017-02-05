package co.com.cybersoft.maintenance.tables.web.domain.sorter;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;
import co.com.cybersoft.maintenance.tables.core.domain.TypeSorterDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for sorter
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SorterInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private Long id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	private Long companyId;


	private String company;


	private List<CompanyDetails> companyList;
	private Long typeSorterId;


	private String typeSorter;


	private List<TypeSorterDetails> typeSorterList;
	@Length(max=20)
	@NotEmpty
	private String nameSorter;


	private Boolean active;


	public List<CompanyDetails> getCompanyList() {
		return companyList;
	}
	public void setCompanyList(
				List<CompanyDetails> companyList) {
			this.companyList = companyList;
	}

	public String getCompany() {
		return company;	
	}
		
	public void setCompany(String company) {
		this.company = company;	
	}

	public Long getCompanyId() {
		return companyId;	
	}
		
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;	
	}

	public List<TypeSorterDetails> getTypeSorterList() {
		return typeSorterList;
	}
	public void setTypeSorterList(
				List<TypeSorterDetails> typeSorterList) {
			this.typeSorterList = typeSorterList;
	}

	public String getTypeSorter() {
		return typeSorter;	
	}
		
	public void setTypeSorter(String typeSorter) {
		this.typeSorter = typeSorter;	
	}

	public Long getTypeSorterId() {
		return typeSorterId;	
	}
		
	public void setTypeSorterId(Long typeSorterId) {
		this.typeSorterId = typeSorterId;	
	}

	public String getNameSorter() {
		return nameSorter;	
	}
		
	public void setNameSorter(String nameSorter) {
		this.nameSorter = nameSorter;	
	}

	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}


	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
		
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCalledFrom() {
		return calledFrom;
	}

	public void setCalledFrom(String calledFrom) {
		this.calledFrom = calledFrom;
	}

	public Boolean getCreated() {
		return created;
	}

	public void setCreated(Boolean created) {
		this.created = created;
	}
	
}