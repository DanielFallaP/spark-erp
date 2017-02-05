package co.com.cybersoft.maintenance.tables.web.domain.authorizerCostCenter;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.NotEmpty;
import co.com.cybersoft.maintenance.tables.core.domain.CostCentersCustomersDetails;
import co.com.cybersoft.maintenance.tables.core.domain.ResponsibleDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for authorizerCostCenter
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class AuthorizerCostCenterInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private Long id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	private Long costCenterId;


	private String costCenter;


	private List<CostCentersCustomersDetails> costCenterList;
	private Long responsibleId;


	private String responsible;


	private List<ResponsibleDetails> responsibleList;
	private Boolean stateAuthorizerCostCenter;


	private Boolean active;


	public List<CostCentersCustomersDetails> getCostCenterList() {
		return costCenterList;
	}
	public void setCostCenterList(
				List<CostCentersCustomersDetails> costCenterList) {
			this.costCenterList = costCenterList;
	}

	public String getCostCenter() {
		return costCenter;	
	}
		
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;	
	}

	public Long getCostCenterId() {
		return costCenterId;	
	}
		
	public void setCostCenterId(Long costCenterId) {
		this.costCenterId = costCenterId;	
	}

	public List<ResponsibleDetails> getResponsibleList() {
		return responsibleList;
	}
	public void setResponsibleList(
				List<ResponsibleDetails> responsibleList) {
			this.responsibleList = responsibleList;
	}

	public String getResponsible() {
		return responsible;	
	}
		
	public void setResponsible(String responsible) {
		this.responsible = responsible;	
	}

	public Long getResponsibleId() {
		return responsibleId;	
	}
		
	public void setResponsibleId(Long responsibleId) {
		this.responsibleId = responsibleId;	
	}

	public Boolean getStateAuthorizerCostCenter() {
		return stateAuthorizerCostCenter;	
	}
		
	public void setStateAuthorizerCostCenter(Boolean stateAuthorizerCostCenter) {
		this.stateAuthorizerCostCenter = stateAuthorizerCostCenter;	
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