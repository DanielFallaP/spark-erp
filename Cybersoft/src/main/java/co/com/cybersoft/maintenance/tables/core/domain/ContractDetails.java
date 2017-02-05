package co.com.cybersoft.maintenance.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.maintenance.tables.persistence.domain.Contract;
import co.com.cybersoft.util.EmbeddedField;
import java.lang.reflect.Method;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;
import co.com.cybersoft.maintenance.tables.core.domain.ResponsibleDetails;
import co.com.cybersoft.maintenance.tables.core.domain.CostCentersCustomersDetails;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ContractDetails {
	
	private Long companyId;


	private String company;


	private String description;


	private Date contractStartDate;


	private Date contractEndDate;


	private Double yearContractValue;


	private Long responsibleId;


	private String responsible;


	private Long costCenterId;


	private String costCenter;


	private Boolean stateContract;


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
	
	public Long getCompanyId() {
		return companyId;	
	}
		
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;	
	}
	public String getCompany() {
		return company;	
	}
		
	public void setCompany(String company) {
		this.company = company;	
	}
	public String getDescription() {
		return description;	
	}
		
	public void setDescription(String description) {
		this.description = description;	
	}
	public Date getContractStartDate() {
		return contractStartDate;	
	}
		
	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;	
	}
	public Date getContractEndDate() {
		return contractEndDate;	
	}
		
	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;	
	}
	public Double getYearContractValue() {
		return yearContractValue;	
	}
		
	public void setYearContractValue(Double yearContractValue) {
		this.yearContractValue = yearContractValue;	
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
	public Boolean getStateContract() {
		return stateContract;	
	}
		
	public void setStateContract(Boolean stateContract) {
		this.stateContract = stateContract;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public ContractDetails toContractDetails(Contract entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String _embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method _method = ContractDetails.class.getMethod("get"+embeddedField.getName());
				String _invoke = (String) _method.invoke(this);
				_embedded+=" - "+_invoke;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.company=entity.getCompany().getCompanyName()+_embedded;
		this.companyId=entity.getCompany().getId();
		this.description=description+_embedded;
		this.responsible=entity.getResponsible().getThird().getNameThird()+_embedded;
		this.responsibleId=entity.getResponsible().getId();
		this.costCenter=entity.getCostCenter().getNameCostCenter()+_embedded;
		this.costCenterId=entity.getCostCenter().getId();

		return this;
	}
}