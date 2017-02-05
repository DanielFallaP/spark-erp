package co.com.cybersoft.maintenance.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.maintenance.tables.persistence.domain.FailureCauseTechnicalaction;
import co.com.cybersoft.util.EmbeddedField;
import java.lang.reflect.Method;
import co.com.cybersoft.maintenance.tables.core.domain.FailureCauseDetails;
import co.com.cybersoft.maintenance.tables.core.domain.TechnicalActionDetails;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class FailureCauseTechnicalactionDetails {
	
	private Long failureCauseId;


	private String failureCause;


	private Long technicalActionId;


	private String technicalAction;


	private Integer orderFailureCauseTechnicalactions;


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
	
	public Long getFailureCauseId() {
		return failureCauseId;	
	}
		
	public void setFailureCauseId(Long failureCauseId) {
		this.failureCauseId = failureCauseId;	
	}
	public String getFailureCause() {
		return failureCause;	
	}
		
	public void setFailureCause(String failureCause) {
		this.failureCause = failureCause;	
	}
	public Long getTechnicalActionId() {
		return technicalActionId;	
	}
		
	public void setTechnicalActionId(Long technicalActionId) {
		this.technicalActionId = technicalActionId;	
	}
	public String getTechnicalAction() {
		return technicalAction;	
	}
		
	public void setTechnicalAction(String technicalAction) {
		this.technicalAction = technicalAction;	
	}
	public Integer getOrderFailureCauseTechnicalactions() {
		return orderFailureCauseTechnicalactions;	
	}
		
	public void setOrderFailureCauseTechnicalactions(Integer orderFailureCauseTechnicalactions) {
		this.orderFailureCauseTechnicalactions = orderFailureCauseTechnicalactions;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public FailureCauseTechnicalactionDetails toFailureCauseTechnicalactionDetails(FailureCauseTechnicalaction entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String _embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method _method = FailureCauseTechnicalactionDetails.class.getMethod("get"+embeddedField.getName());
				String _invoke = (String) _method.invoke(this);
				_embedded+=" - "+_invoke;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.failureCause=entity.getFailureCause().getNameFailureCause()+_embedded;
		this.failureCauseId=entity.getFailureCause().getId();
		this.technicalAction=entity.getTechnicalAction().getTechnicalActionName()+_embedded;
		this.technicalActionId=entity.getTechnicalAction().getId();

		return this;
	}
}