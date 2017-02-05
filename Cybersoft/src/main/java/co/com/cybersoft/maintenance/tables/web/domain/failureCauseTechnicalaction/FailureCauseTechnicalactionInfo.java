package co.com.cybersoft.maintenance.tables.web.domain.failureCauseTechnicalaction;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import co.com.cybersoft.maintenance.tables.core.domain.FailureCauseDetails;
import co.com.cybersoft.maintenance.tables.core.domain.TechnicalActionDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for failureCauseTechnicalaction
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class FailureCauseTechnicalactionInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private Long id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	private Long failureCauseId;


	private String failureCause;


	private List<FailureCauseDetails> failureCauseList;
	private Long technicalActionId;


	private String technicalAction;


	private List<TechnicalActionDetails> technicalActionList;
	@NotNull
	private Integer orderFailureCauseTechnicalactions;


	private Boolean active;


	public List<FailureCauseDetails> getFailureCauseList() {
		return failureCauseList;
	}
	public void setFailureCauseList(
				List<FailureCauseDetails> failureCauseList) {
			this.failureCauseList = failureCauseList;
	}

	public String getFailureCause() {
		return failureCause;	
	}
		
	public void setFailureCause(String failureCause) {
		this.failureCause = failureCause;	
	}

	public Long getFailureCauseId() {
		return failureCauseId;	
	}
		
	public void setFailureCauseId(Long failureCauseId) {
		this.failureCauseId = failureCauseId;	
	}

	public List<TechnicalActionDetails> getTechnicalActionList() {
		return technicalActionList;
	}
	public void setTechnicalActionList(
				List<TechnicalActionDetails> technicalActionList) {
			this.technicalActionList = technicalActionList;
	}

	public String getTechnicalAction() {
		return technicalAction;	
	}
		
	public void setTechnicalAction(String technicalAction) {
		this.technicalAction = technicalAction;	
	}

	public Long getTechnicalActionId() {
		return technicalActionId;	
	}
		
	public void setTechnicalActionId(Long technicalActionId) {
		this.technicalActionId = technicalActionId;	
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