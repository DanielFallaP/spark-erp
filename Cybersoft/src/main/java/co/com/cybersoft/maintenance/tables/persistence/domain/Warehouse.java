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
import co.com.cybersoft.maintenance.tables.persistence.domain.PhysicalLocation;
import co.com.cybersoft.maintenance.tables.persistence.domain.CostingType;
import co.com.cybersoft.maintenance.tables.persistence.domain.StoreType;

import co.com.cybersoft.maintenance.tables.core.domain.WarehouseDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Entity
public class Warehouse {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="COMPANY_ID" , nullable=false)
	private Company company;

	private Integer code;

	private String storeName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PHYSICALLOCATION_ID" , nullable=false)
	private PhysicalLocation physicalLocation;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="COSTINGTYPE_ID" , nullable=false)
	private CostingType costingType;

	@Column(name="f_comment")
	private String comment;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="STORETYPE_ID" , nullable=false)
	private StoreType storeType;

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
	public Integer getCode() {
		return code;	
	}
		
	public void setCode(Integer code) {
		this.code = code;	
	}
	public String getStoreName() {
		return storeName;	
	}
		
	public void setStoreName(String storeName) {
		this.storeName = storeName;	
	}
	public PhysicalLocation getPhysicalLocation() {
		return physicalLocation;	
	}
		
	public void setPhysicalLocation(PhysicalLocation physicalLocation) {
		this.physicalLocation = physicalLocation;	
	}
	public CostingType getCostingType() {
		return costingType;	
	}
		
	public void setCostingType(CostingType costingType) {
		this.costingType = costingType;	
	}
	public String getComment() {
		return comment;	
	}
		
	public void setComment(String comment) {
		this.comment = comment;	
	}
	public StoreType getStoreType() {
		return storeType;	
	}
		
	public void setStoreType(StoreType storeType) {
		this.storeType = storeType;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public Warehouse fromWarehouseDetails(WarehouseDetails details){
		BeanUtils.copyProperties(details, this);
		

		Company company0=new Company();company0.setId(details.getCompanyId());this.company=company0; 
		PhysicalLocation physicalLocation1=new PhysicalLocation();physicalLocation1.setId(details.getPhysicalLocationId());this.physicalLocation=physicalLocation1; 
		CostingType costingType2=new CostingType();costingType2.setId(details.getCostingTypeId());this.costingType=costingType2; 
		StoreType storeType3=new StoreType();storeType3.setId(details.getStoreTypeId());this.storeType=storeType3; 
		
		return this;
	}

}