package co.com.cybersoft.maintenance.tables.web.domain.warehouse;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;
import co.com.cybersoft.maintenance.tables.core.domain.PhysicalLocationDetails;
import co.com.cybersoft.maintenance.tables.core.domain.CostingTypeDetails;
import co.com.cybersoft.maintenance.tables.core.domain.StoreTypeDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for warehouse
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class WarehouseInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private Long id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	private Long companyId;


	private String company;


	private List<CompanyDetails> companyList;
	@NotNull
	private Integer code;


	@Length(max=20)
	@NotEmpty
	private String storeName;


	private Long physicalLocationId;


	private String physicalLocation;


	private List<PhysicalLocationDetails> physicalLocationList;
	private Long costingTypeId;


	private String costingType;


	private List<CostingTypeDetails> costingTypeList;
	@Length(max=4000)
	@NotEmpty
	private String comment;


	private Long storeTypeId;


	private String storeType;


	private List<StoreTypeDetails> storeTypeList;
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

	public Integer getCode() {
		return code;	
	}
		
	public void setCode(Integer code) {
		this.code = code;	
	}

	public String getStoreName() {
		return storeName;	
	}
		
	public void setStoreName(String storeName) {
		this.storeName = storeName;	
	}

	public List<PhysicalLocationDetails> getPhysicalLocationList() {
		return physicalLocationList;
	}
	public void setPhysicalLocationList(
				List<PhysicalLocationDetails> physicalLocationList) {
			this.physicalLocationList = physicalLocationList;
	}

	public String getPhysicalLocation() {
		return physicalLocation;	
	}
		
	public void setPhysicalLocation(String physicalLocation) {
		this.physicalLocation = physicalLocation;	
	}

	public Long getPhysicalLocationId() {
		return physicalLocationId;	
	}
		
	public void setPhysicalLocationId(Long physicalLocationId) {
		this.physicalLocationId = physicalLocationId;	
	}

	public List<CostingTypeDetails> getCostingTypeList() {
		return costingTypeList;
	}
	public void setCostingTypeList(
				List<CostingTypeDetails> costingTypeList) {
			this.costingTypeList = costingTypeList;
	}

	public String getCostingType() {
		return costingType;	
	}
		
	public void setCostingType(String costingType) {
		this.costingType = costingType;	
	}

	public Long getCostingTypeId() {
		return costingTypeId;	
	}
		
	public void setCostingTypeId(Long costingTypeId) {
		this.costingTypeId = costingTypeId;	
	}

	public String getComment() {
		return comment;	
	}
		
	public void setComment(String comment) {
		this.comment = comment;	
	}

	public List<StoreTypeDetails> getStoreTypeList() {
		return storeTypeList;
	}
	public void setStoreTypeList(
				List<StoreTypeDetails> storeTypeList) {
			this.storeTypeList = storeTypeList;
	}

	public String getStoreType() {
		return storeType;	
	}
		
	public void setStoreType(String storeType) {
		this.storeType = storeType;	
	}

	public Long getStoreTypeId() {
		return storeTypeId;	
	}
		
	public void setStoreTypeId(Long storeTypeId) {
		this.storeTypeId = storeTypeId;	
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