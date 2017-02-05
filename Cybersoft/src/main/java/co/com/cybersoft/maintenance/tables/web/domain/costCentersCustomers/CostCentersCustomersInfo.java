package co.com.cybersoft.maintenance.tables.web.domain.costCentersCustomers;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;
import co.com.cybersoft.maintenance.tables.core.domain.StateCostCentersDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for costCentersCustomers
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CostCentersCustomersInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private Long id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	private Long companyId;


	private String company;


	private List<CompanyDetails> companyList;
	@Length(max=40)
	@NotEmpty
	private String description;


	@Length(max=50)
	@NotEmpty
	private String nameCostCenter;


	@Length(max=40)
	@NotEmpty
	private String subCostCentersCustomers;


	@Length(max=50)
	@NotEmpty
	private String subDescription;


	@Length(max=40)
	@NotEmpty
	private String contact;


	@Length(max=50)
	@NotEmpty
	private String areaDepartment;


	@Length(max=60)
	@NotEmpty
	private String address;


	@Length(max=30)
	@NotEmpty
	private String city;


	@Length(max=25)
	@NotEmpty
	private String phone;


	@NotNull
	private Integer classis;


	private Long stateId;


	private String state;


	private List<StateCostCentersDetails> stateList;
	@Length(max=4000)
	@NotEmpty
	private String comments;


	private Boolean stateCostCenter;


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

	public String getDescription() {
		return description;	
	}
		
	public void setDescription(String description) {
		this.description = description;	
	}

	public String getNameCostCenter() {
		return nameCostCenter;	
	}
		
	public void setNameCostCenter(String nameCostCenter) {
		this.nameCostCenter = nameCostCenter;	
	}

	public String getSubCostCentersCustomers() {
		return subCostCentersCustomers;	
	}
		
	public void setSubCostCentersCustomers(String subCostCentersCustomers) {
		this.subCostCentersCustomers = subCostCentersCustomers;	
	}

	public String getSubDescription() {
		return subDescription;	
	}
		
	public void setSubDescription(String subDescription) {
		this.subDescription = subDescription;	
	}

	public String getContact() {
		return contact;	
	}
		
	public void setContact(String contact) {
		this.contact = contact;	
	}

	public String getAreaDepartment() {
		return areaDepartment;	
	}
		
	public void setAreaDepartment(String areaDepartment) {
		this.areaDepartment = areaDepartment;	
	}

	public String getAddress() {
		return address;	
	}
		
	public void setAddress(String address) {
		this.address = address;	
	}

	public String getCity() {
		return city;	
	}
		
	public void setCity(String city) {
		this.city = city;	
	}

	public String getPhone() {
		return phone;	
	}
		
	public void setPhone(String phone) {
		this.phone = phone;	
	}

	public Integer getClassis() {
		return classis;	
	}
		
	public void setClassis(Integer classis) {
		this.classis = classis;	
	}

	public List<StateCostCentersDetails> getStateList() {
		return stateList;
	}
	public void setStateList(
				List<StateCostCentersDetails> stateList) {
			this.stateList = stateList;
	}

	public String getState() {
		return state;	
	}
		
	public void setState(String state) {
		this.state = state;	
	}

	public Long getStateId() {
		return stateId;	
	}
		
	public void setStateId(Long stateId) {
		this.stateId = stateId;	
	}

	public String getComments() {
		return comments;	
	}
		
	public void setComments(String comments) {
		this.comments = comments;	
	}

	public Boolean getStateCostCenter() {
		return stateCostCenter;	
	}
		
	public void setStateCostCenter(Boolean stateCostCenter) {
		this.stateCostCenter = stateCostCenter;	
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