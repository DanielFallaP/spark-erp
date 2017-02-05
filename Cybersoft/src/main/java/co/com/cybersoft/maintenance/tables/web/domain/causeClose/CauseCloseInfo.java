package co.com.cybersoft.maintenance.tables.web.domain.causeClose;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;
import co.com.cybersoft.maintenance.tables.core.domain.TypeCauseCloseDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for causeClose
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CauseCloseInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private Long id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	private Long companyId;


	private String company;


	private List<CompanyDetails> companyList;
	@Length(max=50)
	@NotEmpty
	private String nameCauseClose;


	private Long typeCauseCloseId;


	private String typeCauseClose;


	private List<TypeCauseCloseDetails> typeCauseCloseList;
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

	public String getNameCauseClose() {
		return nameCauseClose;	
	}
		
	public void setNameCauseClose(String nameCauseClose) {
		this.nameCauseClose = nameCauseClose;	
	}

	public List<TypeCauseCloseDetails> getTypeCauseCloseList() {
		return typeCauseCloseList;
	}
	public void setTypeCauseCloseList(
				List<TypeCauseCloseDetails> typeCauseCloseList) {
			this.typeCauseCloseList = typeCauseCloseList;
	}

	public String getTypeCauseClose() {
		return typeCauseClose;	
	}
		
	public void setTypeCauseClose(String typeCauseClose) {
		this.typeCauseClose = typeCauseClose;	
	}

	public Long getTypeCauseCloseId() {
		return typeCauseCloseId;	
	}
		
	public void setTypeCauseCloseId(Long typeCauseCloseId) {
		this.typeCauseCloseId = typeCauseCloseId;	
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