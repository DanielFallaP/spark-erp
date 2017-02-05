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
import co.com.cybersoft.maintenance.tables.persistence.domain.MeasurementUnit;

import co.com.cybersoft.maintenance.tables.core.domain.PhysicalLocationDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Entity
public class PhysicalLocation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="COMPANY_ID" , nullable=false)
	private Company company;

	private String namePhysicalLocation;

	private String description;

	private String descriptionEnglish;

	private String descriptionShort;

	private Double physicalLocationArea;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="MEASUREMENTUNIT_ID" , nullable=false)
	private MeasurementUnit measurementUnit;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="CAPACITYPHYSICALLOCATION_ID" , nullable=false)
	private MeasurementUnit capacityPhysicalLocation;

	private Boolean statePhysicalLocation;

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
	public String getNamePhysicalLocation() {
		return namePhysicalLocation;	
	}
		
	public void setNamePhysicalLocation(String namePhysicalLocation) {
		this.namePhysicalLocation = namePhysicalLocation;	
	}
	public String getDescription() {
		return description;	
	}
		
	public void setDescription(String description) {
		this.description = description;	
	}
	public String getDescriptionEnglish() {
		return descriptionEnglish;	
	}
		
	public void setDescriptionEnglish(String descriptionEnglish) {
		this.descriptionEnglish = descriptionEnglish;	
	}
	public String getDescriptionShort() {
		return descriptionShort;	
	}
		
	public void setDescriptionShort(String descriptionShort) {
		this.descriptionShort = descriptionShort;	
	}
	public Double getPhysicalLocationArea() {
		return physicalLocationArea;	
	}
		
	public void setPhysicalLocationArea(Double physicalLocationArea) {
		this.physicalLocationArea = physicalLocationArea;	
	}
	public MeasurementUnit getMeasurementUnit() {
		return measurementUnit;	
	}
		
	public void setMeasurementUnit(MeasurementUnit measurementUnit) {
		this.measurementUnit = measurementUnit;	
	}
	public MeasurementUnit getCapacityPhysicalLocation() {
		return capacityPhysicalLocation;	
	}
		
	public void setCapacityPhysicalLocation(MeasurementUnit capacityPhysicalLocation) {
		this.capacityPhysicalLocation = capacityPhysicalLocation;	
	}
	public Boolean getStatePhysicalLocation() {
		return statePhysicalLocation;	
	}
		
	public void setStatePhysicalLocation(Boolean statePhysicalLocation) {
		this.statePhysicalLocation = statePhysicalLocation;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public PhysicalLocation fromPhysicalLocationDetails(PhysicalLocationDetails details){
		BeanUtils.copyProperties(details, this);
		

		Company company0=new Company();company0.setId(details.getCompanyId());this.company=company0; 
		MeasurementUnit measurementUnit1=new MeasurementUnit();measurementUnit1.setId(details.getMeasurementUnitId());this.measurementUnit=measurementUnit1; 
		MeasurementUnit measurementUnit2=new MeasurementUnit();measurementUnit2.setId(details.getCapacityPhysicalLocationId());this.capacityPhysicalLocation=measurementUnit2; 
		
		return this;
	}

}