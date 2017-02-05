package co.com.cybersoft.maintenance.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.maintenance.tables.persistence.domain.Job;
import co.com.cybersoft.util.EmbeddedField;
import java.lang.reflect.Method;
import co.com.cybersoft.maintenance.tables.core.domain.ResponsibleCenterDetails;
import co.com.cybersoft.maintenance.tables.core.domain.TypeWorkDetails;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class JobDetails {
	
	private Long responsibleCenterId;


	private String responsibleCenter;


	private String nameJob;


	private Double valueTime1;


	private Double valueTime2;


	private Double valueTime3;


	private Long typeWorkId;


	private String typeWork;


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
	
	public Long getResponsibleCenterId() {
		return responsibleCenterId;	
	}
		
	public void setResponsibleCenterId(Long responsibleCenterId) {
		this.responsibleCenterId = responsibleCenterId;	
	}
	public String getResponsibleCenter() {
		return responsibleCenter;	
	}
		
	public void setResponsibleCenter(String responsibleCenter) {
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
	public Long getTypeWorkId() {
		return typeWorkId;	
	}
		
	public void setTypeWorkId(Long typeWorkId) {
		this.typeWorkId = typeWorkId;	
	}
	public String getTypeWork() {
		return typeWork;	
	}
		
	public void setTypeWork(String typeWork) {
		this.typeWork = typeWork;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public JobDetails toJobDetails(Job entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String _embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method _method = JobDetails.class.getMethod("get"+embeddedField.getName());
				String _invoke = (String) _method.invoke(this);
				_embedded+=" - "+_invoke;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.responsibleCenter=entity.getResponsibleCenter().getNameResponsibleCenter()+_embedded;
		this.responsibleCenterId=entity.getResponsibleCenter().getId();
		this.nameJob=nameJob+_embedded;
		this.typeWork=entity.getTypeWork().getTypeWork()+_embedded;
		this.typeWorkId=entity.getTypeWork().getId();

		return this;
	}
}