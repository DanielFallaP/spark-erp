package co.com.cybersoft.maintenance.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.maintenance.tables.persistence.domain.AuthorizerCostCenter;
import co.com.cybersoft.util.EmbeddedField;
import java.lang.reflect.Method;
import co.com.cybersoft.maintenance.tables.core.domain.CostCentersCustomersDetails;
import co.com.cybersoft.maintenance.tables.core.domain.ResponsibleDetails;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class AuthorizerCostCenterDetails {
	
	private Long costCenterId;


	private String costCenter;


	private Long responsibleId;


	private String responsible;


	private Boolean stateAuthorizerCostCenter;


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
	
	public Long getCostCenterId() {
		return costCenterId;	
	}
		
	public void setCostCenterId(Long costCenterId) {
		this.costCenterId = costCenterId;	
	}
	public String getCostCenter() {
		return costCenter;	
	}
		
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;	
	}
	public Long getResponsibleId() {
		return responsibleId;	
	}
		
	public void setResponsibleId(Long responsibleId) {
		this.responsibleId = responsibleId;	
	}
	public String getResponsible() {
		return responsible;	
	}
		
	public void setResponsible(String responsible) {
		this.responsible = responsible;	
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

	
	public AuthorizerCostCenterDetails toAuthorizerCostCenterDetails(AuthorizerCostCenter entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String _embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method _method = AuthorizerCostCenterDetails.class.getMethod("get"+embeddedField.getName());
				String _invoke = (String) _method.invoke(this);
				_embedded+=" - "+_invoke;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.costCenter=entity.getCostCenter().getNameCostCenter()+_embedded;
		this.costCenterId=entity.getCostCenter().getId();
		this.responsible=entity.getResponsible().getThird().getNameThird()+_embedded;
		this.responsibleId=entity.getResponsible().getId();

		return this;
	}
}