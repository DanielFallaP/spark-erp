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
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeWork;

import co.com.cybersoft.maintenance.tables.core.domain.OtherConceptsDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Entity
public class OtherConcepts {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="COMPANY_ID" , nullable=false)
	private Company company;

	private String nameOtherconcepts;

	private Double unitValue;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="UNITMEASURE_ID" , nullable=false)
	private MeasurementUnit unitMeasure;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="TYPEWORK_ID" , nullable=false)
	private TypeWork typeWork;

	private Integer informative;

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
	public String getNameOtherconcepts() {
		return nameOtherconcepts;	
	}
		
	public void setNameOtherconcepts(String nameOtherconcepts) {
		this.nameOtherconcepts = nameOtherconcepts;	
	}
	public Double getUnitValue() {
		return unitValue;	
	}
		
	public void setUnitValue(Double unitValue) {
		this.unitValue = unitValue;	
	}
	public MeasurementUnit getUnitMeasure() {
		return unitMeasure;	
	}
		
	public void setUnitMeasure(MeasurementUnit unitMeasure) {
		this.unitMeasure = unitMeasure;	
	}
	public TypeWork getTypeWork() {
		return typeWork;	
	}
		
	public void setTypeWork(TypeWork typeWork) {
		this.typeWork = typeWork;	
	}
	public Integer getInformative() {
		return informative;	
	}
		
	public void setInformative(Integer informative) {
		this.informative = informative;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public OtherConcepts fromOtherConceptsDetails(OtherConceptsDetails details){
		BeanUtils.copyProperties(details, this);
		

		Company company0=new Company();company0.setId(details.getCompanyId());this.company=company0; 
		MeasurementUnit measurementUnit1=new MeasurementUnit();measurementUnit1.setId(details.getUnitMeasureId());this.unitMeasure=measurementUnit1; 
		TypeWork typeWork2=new TypeWork();typeWork2.setId(details.getTypeWorkId());this.typeWork=typeWork2; 
		
		return this;
	}

}