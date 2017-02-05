package co.com.cybersoft.maintenance.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.maintenance.tables.persistence.domain.ReferenceOperation;
import co.com.cybersoft.util.EmbeddedField;
import java.lang.reflect.Method;
import co.com.cybersoft.maintenance.tables.core.domain.ReferenceDetails;
import co.com.cybersoft.maintenance.tables.core.domain.OperationDetails;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ReferenceOperationDetails {
	
	private Long referenceId;


	private String reference;


	private Long operationId;


	private String operation;


	private Integer numOrderRefenceOperation;


	private Boolean active;


		
	private Date dateOfModification;
	
	private Long id;
	
	private String userName;
	
	private Date dateOfCreation;
	
	private String createdBy;
	
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
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public Long getReferenceId() {
		return referenceId;	
	}
		
	public void setReferenceId(Long referenceId) {
		this.referenceId = referenceId;	
	}
	public String getReference() {
		return reference;	
	}
		
	public void setReference(String reference) {
		this.reference = reference;	
	}
	public Long getOperationId() {
		return operationId;	
	}
		
	public void setOperationId(Long operationId) {
		this.operationId = operationId;	
	}
	public String getOperation() {
		return operation;	
	}
		
	public void setOperation(String operation) {
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

	
	public ReferenceOperationDetails toReferenceOperationDetails(ReferenceOperation entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String _embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method _method = ReferenceOperationDetails.class.getMethod("get"+embeddedField.getName());
				String _invoke = (String) _method.invoke(this);
				_embedded+=" - "+_invoke;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.reference=entity.getReference().getNameReference()+_embedded;
		this.referenceId=entity.getReference().getId();
		this.operation=entity.getOperation().getNameOperation()+_embedded;
		this.operationId=entity.getOperation().getId();

		return this;
	}
}