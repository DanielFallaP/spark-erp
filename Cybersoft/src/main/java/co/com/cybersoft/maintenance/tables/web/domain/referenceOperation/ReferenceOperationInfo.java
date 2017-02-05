package co.com.cybersoft.maintenance.tables.web.domain.referenceOperation;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import co.com.cybersoft.maintenance.tables.core.domain.ReferenceDetails;
import co.com.cybersoft.maintenance.tables.core.domain.OperationDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for referenceOperation
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ReferenceOperationInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private Long id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	private Long referenceId;


	private String reference;


	private List<ReferenceDetails> referenceList;
	private Long operationId;


	private String operation;


	private List<OperationDetails> operationList;
	@NotNull
	private Integer numOrderRefenceOperation;


	private Boolean active;


	public List<ReferenceDetails> getReferenceList() {
		return referenceList;
	}
	public void setReferenceList(
				List<ReferenceDetails> referenceList) {
			this.referenceList = referenceList;
	}

	public String getReference() {
		return reference;	
	}
		
	public void setReference(String reference) {
		this.reference = reference;	
	}

	public Long getReferenceId() {
		return referenceId;	
	}
		
	public void setReferenceId(Long referenceId) {
		this.referenceId = referenceId;	
	}

	public List<OperationDetails> getOperationList() {
		return operationList;
	}
	public void setOperationList(
				List<OperationDetails> operationList) {
			this.operationList = operationList;
	}

	public String getOperation() {
		return operation;	
	}
		
	public void setOperation(String operation) {
		this.operation = operation;	
	}

	public Long getOperationId() {
		return operationId;	
	}
		
	public void setOperationId(Long operationId) {
		this.operationId = operationId;	
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


	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
		
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCalledFrom() {
		return calledFrom;
	}

	public void setCalledFrom(String calledFrom) {
		this.calledFrom = calledFrom;
	}

	public Boolean getCreated() {
		return created;
	}

	public void setCreated(Boolean created) {
		this.created = created;
	}
	
}