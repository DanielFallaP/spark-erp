package co.com.cybersoft.maintenance.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.maintenance.tables.persistence.domain.JobThird;
import co.com.cybersoft.util.EmbeddedField;
import java.lang.reflect.Method;
import co.com.cybersoft.maintenance.tables.core.domain.JobDetails;
import co.com.cybersoft.maintenance.tables.core.domain.ThirdDetails;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class JobThirdDetails {
	
	private Long jobId;


	private String job;


	private Long thirdId;


	private String third;


	private Boolean stateJobThird;


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
	
	public Long getJobId() {
		return jobId;	
	}
		
	public void setJobId(Long jobId) {
		this.jobId = jobId;	
	}
	public String getJob() {
		return job;	
	}
		
	public void setJob(String job) {
		this.job = job;	
	}
	public Long getThirdId() {
		return thirdId;	
	}
		
	public void setThirdId(Long thirdId) {
		this.thirdId = thirdId;	
	}
	public String getThird() {
		return third;	
	}
		
	public void setThird(String third) {
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

	
	public JobThirdDetails toJobThirdDetails(JobThird entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String _embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method _method = JobThirdDetails.class.getMethod("get"+embeddedField.getName());
				String _invoke = (String) _method.invoke(this);
				_embedded+=" - "+_invoke;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.job=entity.getJob().getNameJob()+_embedded;
		this.jobId=entity.getJob().getId();
		this.third=entity.getThird().getNameThird()+_embedded;
		this.thirdId=entity.getThird().getId();

		return this;
	}
}