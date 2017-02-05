package co.com.cybersoft.maintenance.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.maintenance.tables.persistence.domain.EffectFailTechnicalAction;
import co.com.cybersoft.util.EmbeddedField;
import java.lang.reflect.Method;
import co.com.cybersoft.maintenance.tables.core.domain.EffectFailDetails;
import co.com.cybersoft.maintenance.tables.core.domain.TechnicalActionDetails;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class EffectFailTechnicalActionDetails {
	
	private Long effectFailId;


	private String effectFail;


	private Long technicalActionId;


	private String technicalAction;


	private Integer orderEffectTechnicalActionFails;


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
	
	public Long getEffectFailId() {
		return effectFailId;	
	}
		
	public void setEffectFailId(Long effectFailId) {
		this.effectFailId = effectFailId;	
	}
	public String getEffectFail() {
		return effectFail;	
	}
		
	public void setEffectFail(String effectFail) {
		this.effectFail = effectFail;	
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

	
	public EffectFailTechnicalActionDetails toEffectFailTechnicalActionDetails(EffectFailTechnicalAction entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String _embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method _method = EffectFailTechnicalActionDetails.class.getMethod("get"+embeddedField.getName());
				String _invoke = (String) _method.invoke(this);
				_embedded+=" - "+_invoke;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.effectFail=entity.getEffectFail().getEffectFailName()+_embedded;
		this.effectFailId=entity.getEffectFail().getId();
		this.technicalAction=entity.getTechnicalAction().getTechnicalActionName()+_embedded;
		this.technicalActionId=entity.getTechnicalAction().getId();

		return this;
	}
}