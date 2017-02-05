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


import co.com.cybersoft.maintenance.tables.persistence.domain.Job;
import co.com.cybersoft.maintenance.tables.persistence.domain.Third;

import co.com.cybersoft.maintenance.tables.core.domain.JobThirdDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Entity
public class JobThird {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="JOB_ID" , nullable=false)
	private Job job;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="THIRD_ID" , nullable=false)
	private Third third;

	private Boolean stateJobThird;

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
	
	public Job getJob() {
		return job;	
	}
		
	public void setJob(Job job) {
		this.job = job;	
	}
	public Third getThird() {
		return third;	
	}
		
	public void setThird(Third third) {
		this.third = third;	
	}
	public Boolean getStateJobThird() {
		return stateJobThird;	
	}
		
	public void setStateJobThird(Boolean stateJobThird) {
		this.stateJobThird = stateJobThird;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public JobThird fromJobThirdDetails(JobThirdDetails details){
		BeanUtils.copyProperties(details, this);
		

		Job job0=new Job();job0.setId(details.getJobId());this.job=job0; 
		Third third1=new Third();third1.setId(details.getThirdId());this.third=third1; 
		
		return this;
	}

}