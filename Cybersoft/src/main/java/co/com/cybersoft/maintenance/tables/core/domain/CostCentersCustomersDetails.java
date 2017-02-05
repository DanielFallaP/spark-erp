package co.com.cybersoft.maintenance.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.maintenance.tables.persistence.domain.CostCentersCustomers;
import co.com.cybersoft.util.EmbeddedField;
import java.lang.reflect.Method;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;
import co.com.cybersoft.maintenance.tables.core.domain.StateCostCentersDetails;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CostCentersCustomersDetails {
	
	private Long companyId;


	private String company;


	private String description;


	private String nameCostCenter;


	private String subCostCentersCustomers;


	private String subDescription;


	private String contact;


	private String areaDepartment;


	private String address;


	private String city;


	private String phone;


	private Integer classis;


	private Long stateId;


	private String state;


	private String comments;


	private Boolean stateCostCenter;


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
	public Long getStateId() {
		return stateId;	
	}
		
	public void setStateId(Long stateId) {
		this.stateId = stateId;	
	}
	public String getState() {
		return state;	
	}
		
	public void setState(String state) {
		this.state = state;	
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

	
	public CostCentersCustomersDetails toCostCentersCustomersDetails(CostCentersCustomers entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String _embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method _method = CostCentersCustomersDetails.class.getMethod("get"+embeddedField.getName());
				String _invoke = (String) _method.invoke(this);
				_embedded+=" - "+_invoke;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.company=entity.getCompany().getCompanyName()+_embedded;
		this.companyId=entity.getCompany().getId();
		this.description=description+_embedded;
		this.nameCostCenter=nameCostCenter+_embedded;
		this.subCostCentersCustomers=subCostCentersCustomers+_embedded;
		this.subDescription=subDescription+_embedded;
		this.contact=contact+_embedded;
		this.areaDepartment=areaDepartment+_embedded;
		this.address=address+_embedded;
		this.city=city+_embedded;
		this.phone=phone+_embedded;
		this.state=entity.getState().getStateCostCenters()+_embedded;
		this.stateId=entity.getState().getId();
		this.comments=comments+_embedded;

		return this;
	}
}