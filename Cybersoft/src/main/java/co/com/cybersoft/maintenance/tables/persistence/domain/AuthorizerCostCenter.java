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


import co.com.cybersoft.maintenance.tables.persistence.domain.CostCentersCustomers;
import co.com.cybersoft.maintenance.tables.persistence.domain.Responsible;

import co.com.cybersoft.maintenance.tables.core.domain.AuthorizerCostCenterDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Entity
public class AuthorizerCostCenter {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="COSTCENTER_ID" , nullable=false)
	private CostCentersCustomers costCenter;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="RESPONSIBLE_ID" , nullable=false)
	private Responsible responsible;

	private Boolean stateAuthorizerCostCenter;

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
	
	public CostCentersCustomers getCostCenter() {
		return costCenter;	
	}
		
	public void setCostCenter(CostCentersCustomers costCenter) {
		this.costCenter = costCenter;	
	}
	public Responsible getResponsible() {
		return responsible;	
	}
		
	public void setResponsible(Responsible responsible) {
		this.responsible = responsible;	
	}
	public Boolean getStateAuthorizerCostCenter() {
		return stateAuthorizerCostCenter;	
	}
		
	public void setStateAuthorizerCostCenter(Boolean stateAuthorizerCostCenter) {
		this.stateAuthorizerCostCenter = stateAuthorizerCostCenter;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public AuthorizerCostCenter fromAuthorizerCostCenterDetails(AuthorizerCostCenterDetails details){
		BeanUtils.copyProperties(details, this);
		

		CostCentersCustomers costCentersCustomers0=new CostCentersCustomers();costCentersCustomers0.setId(details.getCostCenterId());this.costCenter=costCentersCustomers0; 
		Responsible responsible1=new Responsible();responsible1.setId(details.getResponsibleId());this.responsible=responsible1; 
		
		return this;
	}

}