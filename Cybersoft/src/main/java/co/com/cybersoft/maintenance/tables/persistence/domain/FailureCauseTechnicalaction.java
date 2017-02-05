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


import co.com.cybersoft.maintenance.tables.persistence.domain.FailureCause;
import co.com.cybersoft.maintenance.tables.persistence.domain.TechnicalAction;

import co.com.cybersoft.maintenance.tables.core.domain.FailureCauseTechnicalactionDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Entity
public class FailureCauseTechnicalaction {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="FAILURECAUSE_ID" , nullable=false)
	private FailureCause failureCause;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="TECHNICALACTION_ID" , nullable=false)
	private TechnicalAction technicalAction;

	@Column(name="orderFailureCauseTechnicalacti")
	private Integer orderFailureCauseTechnicalactions;

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
	
	public FailureCause getFailureCause() {
		return failureCause;	
	}
		
	public void setFailureCause(FailureCause failureCause) {
		this.failureCause = failureCause;	
	}
	public TechnicalAction getTechnicalAction() {
		return technicalAction;	
	}
		
	public void setTechnicalAction(TechnicalAction technicalAction) {
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

	
	public FailureCauseTechnicalaction fromFailureCauseTechnicalactionDetails(FailureCauseTechnicalactionDetails details){
		BeanUtils.copyProperties(details, this);
		

		FailureCause failureCause0=new FailureCause();failureCause0.setId(details.getFailureCauseId());this.failureCause=failureCause0; 
		TechnicalAction technicalAction1=new TechnicalAction();technicalAction1.setId(details.getTechnicalActionId());this.technicalAction=technicalAction1; 
		
		return this;
	}

}