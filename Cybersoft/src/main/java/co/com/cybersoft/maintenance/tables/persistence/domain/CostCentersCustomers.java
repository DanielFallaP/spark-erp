package co.com.cybersoft.maintenance.tables.persistence.domain;

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
import co.com.cybersoft.maintenance.tables.persistence.domain.StateCostCenters;

import co.com.cybersoft.maintenance.tables.core.domain.CostCentersCustomersDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Entity
public class CostCentersCustomers {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="COMPANY_ID" , nullable=false)
	private Company company;

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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="STATE_ID" , nullable=false)
	private StateCostCenters state;

	private String comments;

	private Boolean stateCostCenter;

	private Boolean active;


	private Date dateOfModification;
	
	private Date dateOfCreation;
	
	private String userName;
	
	private String createdBy;
	
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
	
	public Company getCompany() {
		return company;	
	}
		
	public void setCompany(Company company) {
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
	public StateCostCenters getState() {
		return state;	
	}
		
	public void setState(StateCostCenters state) {
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

	
	public CostCentersCustomers fromCostCentersCustomersDetails(CostCentersCustomersDetails details){
		BeanUtils.copyProperties(details, this);
		

		Company company0=new Company();company0.setId(details.getCompanyId());this.company=company0; 
		StateCostCenters stateCostCenters1=new StateCostCenters();stateCostCenters1.setId(details.getStateId());this.state=stateCostCenters1; 
		
		return this;
	}

}