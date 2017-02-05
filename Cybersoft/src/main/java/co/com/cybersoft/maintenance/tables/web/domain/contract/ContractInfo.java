package co.com.cybersoft.maintenance.tables.web.domain.contract;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;
import co.com.cybersoft.maintenance.tables.core.domain.ResponsibleDetails;
import co.com.cybersoft.maintenance.tables.core.domain.CostCentersCustomersDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for contract
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ContractInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private Long id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	private Long companyId;


	private String company;


	private List<CompanyDetails> companyList;
	@Length(max=15)
	@NotEmpty
	private String description;


	private Date contractStartDate;


	private Date contractEndDate;


	@NotNull
	private Double yearContractValue;


	private Long responsibleId;


	private String responsible;


	private List<ResponsibleDetails> responsibleList;
	private Long costCenterId;


	private String costCenter;


	private List<CostCentersCustomersDetails> costCenterList;
	private Boolean stateContract;


	private Boolean active;


	public List<CompanyDetails> getCompanyList() {
		return companyList;
	}
	public void setCompanyList(
				List<CompanyDetails> companyList) {
			this.companyList = companyList;
	}

	public String getCompany() {
		return company;	
	}
		
	public void setCompany(String company) {
		this.company = company;	
	}

	public Long getCompanyId() {
		return companyId;	
	}
		
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;	
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