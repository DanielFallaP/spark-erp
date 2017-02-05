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


import co.com.cybersoft.maintenance.tables.persistence.domain.EffectFail;
import co.com.cybersoft.maintenance.tables.persistence.domain.TechnicalAction;

import co.com.cybersoft.maintenance.tables.core.domain.EffectFailTechnicalActionDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Entity
public class EffectFailTechnicalAction {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="EFFECTFAIL_ID" , nullable=false)
	private EffectFail effectFail;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="TECHNICALACTION_ID" , nullable=false)
	private TechnicalAction technicalAction;

	@Column(name="orderEffectTechnicalActionFail")
	private Integer orderEffectTechnicalActionFails;

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
	
	public EffectFail getEffectFail() {
		return effectFail;	
	}
		
	public void setEffectFail(EffectFail effectFail) {
		this.effectFail = effectFail;	
	}
	public TechnicalAction getTechnicalAction() {
		return technicalAction;	
	}
		
	public void setTechnicalAction(TechnicalAction technicalAction) {
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

	
	public EffectFailTechnicalAction fromEffectFailTechnicalActionDetails(EffectFailTechnicalActionDetails details){
		BeanUtils.copyProperties(details, this);
		

		EffectFail effectFail0=new EffectFail();effectFail0.setId(details.getEffectFailId());this.effectFail=effectFail0; 
		TechnicalAction technicalAction1=new TechnicalAction();technicalAction1.setId(details.getTechnicalActionId());this.technicalAction=technicalAction1; 
		
		return this;
	}

}