package co.com.cybersoft.maintenance.tables.web.domain.jobThird;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.NotEmpty;
import co.com.cybersoft.maintenance.tables.core.domain.JobDetails;
import co.com.cybersoft.maintenance.tables.core.domain.ThirdDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for jobThird
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class JobThirdInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private Long id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	private Long jobId;


	private String job;


	private List<JobDetails> jobList;
	private Long thirdId;


	private String third;


	private List<ThirdDetails> thirdList;
	private Boolean stateJobThird;


	private Boolean active;


	public List<JobDetails> getJobList() {
		return jobList;
	}
	public void setJobList(
				List<JobDetails> jobList) {
			this.jobList = jobList;
	}

	public String getJob() {
		return job;	
	}
		
	public void setJob(String job) {
		this.job = job;	
	}

	public Long getJobId() {
		return jobId;	
	}
		
	public void setJobId(Long jobId) {
		this.jobId = jobId;	
	}

	public List<ThirdDetails> getThirdList() {
		return thirdList;
	}
	public void setThirdList(
				List<ThirdDetails> thirdList) {
			this.thirdList = thirdList;
	}

	public String getThird() {
		return third;	
	}
		
	public void setThird(String third) {
		this.third = third;	
	}

	public Long getThirdId() {
		return thirdId;	
	}
		
	public void setThirdId(Long thirdId) {
		this.thirdId = thirdId;	
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