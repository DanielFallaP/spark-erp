package co.com.cybersoft.maintenance.tables.web.domain.effectFailTechnicalAction;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import co.com.cybersoft.maintenance.tables.core.domain.EffectFailDetails;
import co.com.cybersoft.maintenance.tables.core.domain.TechnicalActionDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for effectFailTechnicalAction
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class EffectFailTechnicalActionInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private Long id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	private Long effectFailId;


	private String effectFail;


	private List<EffectFailDetails> effectFailList;
	private Long technicalActionId;


	private String technicalAction;


	private List<TechnicalActionDetails> technicalActionList;
	@NotNull
	private Integer orderEffectTechnicalActionFails;


	private Boolean active;


	public List<EffectFailDetails> getEffectFailList() {
		return effectFailList;
	}
	public void setEffectFailList(
				List<EffectFailDetails> effectFailList) {
			this.effectFailList = effectFailList;
	}

	public String getEffectFail() {
		return effectFail;	
	}
		
	public void setEffectFail(String effectFail) {
		this.effectFail = effectFail;	
	}

	public Long getEffectFailId() {
		return effectFailId;	
	}
		
	public void setEffectFailId(Long effectFailId) {
		this.effectFailId = effectFailId;	
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

	public Integer getOrderEffectTechnicalActionFails() {
		return orderEffectTechnicalActionFails;	
	}
		
	public void setOrderEffectTechnicalActionFails(Integer orderEffectTechnicalActionFails) {
		this.orderEffectTechnicalActionFails = orderEffectTechnicalActionFails;	
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