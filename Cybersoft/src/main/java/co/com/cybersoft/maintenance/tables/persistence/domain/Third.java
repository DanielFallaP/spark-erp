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
import co.com.cybersoft.maintenance.tables.persistence.domain.CostCentersCustomers;

import co.com.cybersoft.maintenance.tables.core.domain.ThirdDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Entity
public class Third {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="COMPANY_ID" , nullable=false)
	private Company company;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="COSTCENTER_ID" , nullable=false)
	private CostCentersCustomers costCenter;

	private String code;

	private String nameThird;

	private Integer typeThird;

	private String contact;

	private String address;

	private String country;

	private String phoneOne;

	private String phoneTwo;

	private String email;

	private Date dateEntry;

	private Date dateRetirement;

	@Column(name="f_comment")
	private String comment;

	private Integer bigContributor;

	private Integer autorretenedor;

	private String typeRegime;

	private String externalAccess;

	private String keyExternalAccess;

	private Boolean stateThird;

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
	public CostCentersCustomers getCostCenter() {
		return costCenter;	
	}
		
	public void setCostCenter(CostCentersCustomers costCenter) {
		this.costCenter = costCenter;	
	}
	public String getCode() {
		return code;	
	}
		
	public void setCode(String code) {
		this.code = code;	
	}
	public String getNameThird() {
		return nameThird;	
	}
		
	public void setNameThird(String nameThird) {
		this.nameThird = nameThird;	
	}
	public Integer getTypeThird() {
		return typeThird;	
	}
		
	public void setTypeThird(Integer typeThird) {
		this.typeThird = typeThird;	
	}
	public String getContact() {
		return contact;	
	}
		
	public void setContact(String contact) {
		this.contact = contact;	
	}
	public String getAddress() {
		return address;	
	}
		
	public void setAddress(String address) {
		this.address = address;	
	}
	public String getCountry() {
		return country;	
	}
		
	public void setCountry(String country) {
		this.country = country;	
	}
	public String getPhoneOne() {
		return phoneOne;	
	}
		
	public void setPhoneOne(String phoneOne) {
		this.phoneOne = phoneOne;	
	}
	public String getPhoneTwo() {
		return phoneTwo;	
	}
		
	public void setPhoneTwo(String phoneTwo) {
		this.phoneTwo = phoneTwo;	
	}
	public String getEmail() {
		return email;	
	}
		
	public void setEmail(String email) {
		this.email = email;	
	}
	public Date getDateEntry() {
		return dateEntry;	
	}
		
	public void setDateEntry(Date dateEntry) {
		this.dateEntry = dateEntry;	
	}
	public Date getDateRetirement() {
		return dateRetirement;	
	}
		
	public void setDateRetirement(Date dateRetirement) {
		this.dateRetirement = dateRetirement;	
	}
	public String getComment() {
		return comment;	
	}
		
	public void setComment(String comment) {
		this.comment = comment;	
	}
	public Integer getBigContributor() {
		return bigContributor;	
	}
		
	public void setBigContributor(Integer bigContributor) {
		this.bigContributor = bigContributor;	
	}
	public Integer getAutorretenedor() {
		return autorretenedor;	
	}
		
	public void setAutorretenedor(Integer autorretenedor) {
		this.autorretenedor = autorretenedor;	
	}
	public String getTypeRegime() {
		return typeRegime;	
	}
		
	public void setTypeRegime(String typeRegime) {
		this.typeRegime = typeRegime;	
	}
	public String getExternalAccess() {
		return externalAccess;	
	}
		
	public void setExternalAccess(String externalAccess) {
		this.externalAccess = externalAccess;	
	}
	public String getKeyExternalAccess() {
		return keyExternalAccess;	
	}
		
	public void setKeyExternalAccess(String keyExternalAccess) {
		this.keyExternalAccess = keyExternalAccess;	
	}
	public Boolean getStateThird() {
		return stateThird;	
	}
		
	public void setStateThird(Boolean stateThird) {
		this.stateThird = stateThird;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public Third fromThirdDetails(ThirdDetails details){
		BeanUtils.copyProperties(details, this);
		

		Company company0=new Company();company0.setId(details.getCompanyId());this.company=company0; 
		CostCentersCustomers costCentersCustomers1=new CostCentersCustomers();costCentersCustomers1.setId(details.getCostCenterId());this.costCenter=costCentersCustomers1; 
		
		return this;
	}

}