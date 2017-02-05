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

import co.com.cybersoft.maintenance.tables.core.domain.MaintenanceActivityDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Entity
public class MaintenanceActivity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="COMPANY_ID" , nullable=false)
	private Company company;

	private String nameMaintenanceActivity;

	private Double standardCost;

	private Integer standarDuration;

	private String durationUnitStandard;

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
	public String getNameMaintenanceActivity() {
		return nameMaintenanceActivity;	
	}
		
	public void setNameMaintenanceActivity(String nameMaintenanceActivity) {
		this.nameMaintenanceActivity = nameMaintenanceActivity;	
	}
	public Double getStandardCost() {
		return standardCost;	
	}
		
	public void setStandardCost(Double standardCost) {
		this.standardCost = standardCost;	
	}
	public Integer getStandarDuration() {
		return standarDuration;	
	}
		
	public void setStandarDuration(Integer standarDuration) {
		this.standarDuration = standarDuration;	
	}
	public String getDurationUnitStandard() {
		return durationUnitStandard;	
	}
		
	public void setDurationUnitStandard(String durationUnitStandard) {
		this.durationUnitStandard = durationUnitStandard;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public MaintenanceActivity fromMaintenanceActivityDetails(MaintenanceActivityDetails details){
		BeanUtils.copyProperties(details, this);
		

		Company company0=new Company();company0.setId(details.getCompanyId());this.company=company0; 
		
		return this;
	}

}