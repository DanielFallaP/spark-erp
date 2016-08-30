package co.com.cybersoft.purchase.tables.web.domain.users;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.NotEmpty;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for users
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UsersInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private Long id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	@NotEmpty
	private String login;


	@NotEmpty
	private String password;


	@NotEmpty
	private String role;


	private Long companyId;


	private String company;


	private List<CompanyDetails> companyList;
	private Boolean currencyRead;


	private Boolean currencyCreate;


	private Boolean currencyUpdate;


	private Boolean currencyExport;


	private Boolean active;


	public String getLogin() {
		return login;	
	}
		
	public void setLogin(String login) {
		this.login = login;	
	}

	public String getPassword() {
		return password;	
	}
		
	public void setPassword(String password) {
		this.password = password;	
	}

	public String getRole() {
		return role;	
	}
		
	public void setRole(String role) {
		this.role = role;	
	}

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

	public Boolean getCurrencyRead() {
		return currencyRead;	
	}
		
	public void setCurrencyRead(Boolean currencyRead) {
		this.currencyRead = currencyRead;	
	}

	public Boolean getCurrencyCreate() {
		return currencyCreate;	
	}
		
	public void setCurrencyCreate(Boolean currencyCreate) {
		this.currencyCreate = currencyCreate;	
	}

	public Boolean getCurrencyUpdate() {
		return currencyUpdate;	
	}
		
	public void setCurrencyUpdate(Boolean currencyUpdate) {
		this.currencyUpdate = currencyUpdate;	
	}

	public Boolean getCurrencyExport() {
		return currencyExport;	
	}
		
	public void setCurrencyExport(Boolean currencyExport) {
		this.currencyExport = currencyExport;	
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