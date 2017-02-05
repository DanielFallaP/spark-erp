package co.com.cybersoft.maintenance.tables.web.domain.job;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import co.com.cybersoft.maintenance.tables.core.domain.ResponsibleCenterDetails;
import co.com.cybersoft.maintenance.tables.core.domain.TypeWorkDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for job
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class JobInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private Long id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	private Long responsibleCenterId;


	private String responsibleCenter;


	private List<ResponsibleCenterDetails> responsibleCenterList;
	@Length(max=25)
	@NotEmpty
	private String nameJob;


	@NotNull
	private Double valueTime1;


	@NotNull
	private Double valueTime2;


	@NotNull
	private Double valueTime3;


	private Long typeWorkId;


	private String typeWork;


	private List<TypeWorkDetails> typeWorkList;
	private Boolean active;


	public List<ResponsibleCenterDetails> getResponsibleCenterList() {
		return responsibleCenterList;
	}
	public void setResponsibleCenterList(
				List<ResponsibleCenterDetails> responsibleCenterList) {
			this.responsibleCenterList = responsibleCenterList;
	}

	public String getResponsibleCenter() {
		return responsibleCenter;	
	}
		
	public void setResponsibleCenter(String responsibleCenter) {
		this.responsibleCenter = responsibleCenter;	
	}

	public Long getResponsibleCenterId() {
		return responsibleCenterId;	
	}
		
	public void setResponsibleCenterId(Long responsibleCenterId) {
		this.responsibleCenterId = responsibleCenterId;	
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

	public List<TypeWorkDetails> getTypeWorkList() {
		return typeWorkList;
	}
	public void setTypeWorkList(
				List<TypeWorkDetails> typeWorkList) {
			this.typeWorkList = typeWorkList;
	}

	public String getTypeWork() {
		return typeWork;	
	}
		
	public void setTypeWork(String typeWork) {
		this.typeWork = typeWork;	
	}

	public Long getTypeWorkId() {
		return typeWorkId;	
	}
		
	public void setTypeWorkId(Long typeWorkId) {
		this.typeWorkId = typeWorkId;	
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