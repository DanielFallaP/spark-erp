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
import co.com.cybersoft.maintenance.tables.persistence.domain.Responsible;
import co.com.cybersoft.maintenance.tables.persistence.domain.CostCentersCustomers;

import co.com.cybersoft.maintenance.tables.core.domain.ContractDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Entity
public class Contract {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="COMPANY_ID" , nullable=false)
	private Company company;

	private String description;

	private Date contractStartDate;

	private Date contractEndDate;

	private Double yearContractValue;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="RESPONSIBLE_ID" , nullable=false)
	private Responsible responsible;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="COSTCENTER_ID" , nullable=false)
	private CostCentersCustomers costCenter;

	private Boolean stateContract;

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
	public Date getContractStartDate() {
		return contractStartDate;	
	}
		
	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;	
	}
	public Date getContractEndDate() {
		return contractEndDate;	
	}
		
	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;	
	}
	public Double getYearContractValue() {
		return yearContractValue;	
	}
		
	public void setYearContractValue(Double yearContractValue) {
		this.yearContractValue = yearContractValue;	
	}
	public Responsible getResponsible() {
		return responsible;	
	}
		
	public void setResponsible(Responsible responsible) {
		this.responsible = responsible;	
	}
	public CostCentersCustomers getCostCenter() {
		return costCenter;	
	}
		
	public void setCostCenter(CostCentersCustomers costCenter) {
		this.costCenter = costCenter;	
	}
	public Boolean getStateContract() {
		return stateContract;	
	}
		
	public void setStateContract(Boolean stateContract) {
		this.stateContract = stateContract;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public Contract fromContractDetails(ContractDetails details){
		BeanUtils.copyProperties(details, this);
		

		Company company0=new Company();company0.setId(details.getCompanyId());this.company=company0; 
		Responsible responsible1=new Responsible();responsible1.setId(details.getResponsibleId());this.responsible=responsible1; 
		CostCentersCustomers costCentersCustomers2=new CostCentersCustomers();costCentersCustomers2.setId(details.getCostCenterId());this.costCenter=costCentersCustomers2; 
		
		return this;
	}

}