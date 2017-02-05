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


import co.com.cybersoft.maintenance.tables.persistence.domain.Reference;
import co.com.cybersoft.maintenance.tables.persistence.domain.Operation;

import co.com.cybersoft.maintenance.tables.core.domain.ReferenceOperationDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Entity
public class ReferenceOperation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="REFERENCE_ID" , nullable=false)
	private Reference reference;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="OPERATION_ID" , nullable=false)
	private Operation operation;

	private Integer numOrderRefenceOperation;

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
	
	public Reference getReference() {
		return reference;	
	}
		
	public void setReference(Reference reference) {
		this.reference = reference;	
	}
	public Operation getOperation() {
		return operation;	
	}
		
	public void setOperation(Operation operation) {
		this.operation = operation;	
	}
	public Integer getNumOrderRefenceOperation() {
		return numOrderRefenceOperation;	
	}
		
	public void setNumOrderRefenceOperation(Integer numOrderRefenceOperation) {
		this.numOrderRefenceOperation = numOrderRefenceOperation;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public ReferenceOperation fromReferenceOperationDetails(ReferenceOperationDetails details){
		BeanUtils.copyProperties(details, this);
		

		Reference reference0=new Reference();reference0.setId(details.getReferenceId());this.reference=reference0; 
		Operation operation1=new Operation();operation1.setId(details.getOperationId());this.operation=operation1; 
		
		return this;
	}

}