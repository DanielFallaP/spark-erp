package co.com.cybersoft.purchase.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.purchase.tables.persistence.domain.Users;
import co.com.cybersoft.util.EmbeddedField;
import java.lang.reflect.Method;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class UsersDetails {
	
	private String login;


	private String password;


	private String role;


	private Long companyId;


	private String company;


	private Boolean currencyRead;


	private Boolean currencyCreate;


	private Boolean currencyUpdate;


	private Boolean currencyExport;


	private Boolean active;


		
	private Date dateOfModification;
	
	private Long id;
	
	private String userName;
	
	private Date dateOfCreation;
	
	private String createdBy;
	
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
	public Long getCompanyId() {
		return companyId;	
	}
		
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;	
	}
	public String getCompany() {
		return company;	
	}
		
	public void setCompany(String company) {
		this.company = company;	
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

	
	public UsersDetails toUsersDetails(Users entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String _embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method _method = UsersDetails.class.getMethod("get"+embeddedField.getName());
				String _invoke = (String) _method.invoke(this);
				_embedded+=" - "+_invoke;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.login=login+_embedded;
		this.password=password+_embedded;
		this.role=role+_embedded;
		this.company=entity+_embedded;
		this.companyId=entity.getCompany().getId();

		return this;
	}
}