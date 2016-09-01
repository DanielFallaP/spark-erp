package co.com.cybersoft.purchase.tables.persistence.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


import co.com.cybersoft.maintenance.tables.persistence.domain.Company;

import co.com.cybersoft.purchase.tables.core.domain.UsersDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Entity
public class Users {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String login;

	private String password;

	private String role;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="COMPANY_ID" , nullable=false)
	private Company company;

	private String currencyPermissions;

	private Boolean active;

	private Date dateOfModification;
	
	private Date dateOfCreation;
	
	private String userName;
	
	private String createdBy;
	
	
	public String getCurrencyPermissions() {
		return currencyPermissions;
	}
	public void setCurrencyPermissions(String currencyPermissions) {
		this.currencyPermissions = currencyPermissions;
	}
	public Date getDateOfModification() {
		return dateOfModification;
	}
	public void setDateOfModification(Date dateOfModification) {
		this.dateOfModification = dateOfModification;
	}
	
	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
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
	public Company getCompany() {
		return company;	
	}
		
	public void setCompany(Company company) {
		this.company = company;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public Users fromUsersDetails(UsersDetails details){
		BeanUtils.copyProperties(details, this);
		
		this.currencyPermissions=(details.getCurrencyCreate()?"C":"")+(details.getCurrencyRead()?"R":"")+(details.getCurrencyUpdate()?"U":"")+(details.getCurrencyExport()?"E":"");

		Company company0=new Company();company0.setId(details.getCompanyId());this.company=company0; 
		
		return this;
	}

}