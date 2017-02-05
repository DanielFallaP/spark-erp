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


import co.com.cybersoft.maintenance.tables.persistence.domain.ResponsibleCenter;
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeWork;

import co.com.cybersoft.maintenance.tables.core.domain.JobDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Entity
public class Job {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="RESPONSIBLECENTER_ID" , nullable=false)
	private ResponsibleCenter responsibleCenter;

	private String nameJob;

	private Double valueTime1;

	private Double valueTime2;

	private Double valueTime3;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="TYPEWORK_ID" , nullable=false)
	private TypeWork typeWork;

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
	
	public ResponsibleCenter getResponsibleCenter() {
		return responsibleCenter;	
	}
		
	public void setResponsibleCenter(ResponsibleCenter responsibleCenter) {
		this.responsibleCenter = responsibleCenter;	
	}
	public String getNameJob() {
		return nameJob;	
	}
		
	public void setNameJob(String nameJob) {
		this.nameJob = nameJob;	
	}
	public Double getValueTime1() {
		return valueTime1;	
	}
		
	public void setValueTime1(Double valueTime1) {
		this.valueTime1 = valueTime1;	
	}
	public Double getValueTime2() {
		return valueTime2;	
	}
		
	public void setValueTime2(Double valueTime2) {
		this.valueTime2 = valueTime2;	
	}
	public Double getValueTime3() {
		return valueTime3;	
	}
		
	public void setValueTime3(Double valueTime3) {
		this.valueTime3 = valueTime3;	
	}
	public TypeWork getTypeWork() {
		return typeWork;	
	}
		
	public void setTypeWork(TypeWork typeWork) {
		this.typeWork = typeWork;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public Job fromJobDetails(JobDetails details){
		BeanUtils.copyProperties(details, this);
		

		ResponsibleCenter responsibleCenter0=new ResponsibleCenter();responsibleCenter0.setId(details.getResponsibleCenterId());this.responsibleCenter=responsibleCenter0; 
		TypeWork typeWork1=new TypeWork();typeWork1.setId(details.getTypeWorkId());this.typeWork=typeWork1; 
		
		return this;
	}

}