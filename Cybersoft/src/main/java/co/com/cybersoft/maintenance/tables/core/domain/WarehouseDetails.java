package co.com.cybersoft.maintenance.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.maintenance.tables.persistence.domain.Warehouse;
import co.com.cybersoft.util.EmbeddedField;
import java.lang.reflect.Method;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;
import co.com.cybersoft.maintenance.tables.core.domain.PhysicalLocationDetails;
import co.com.cybersoft.maintenance.tables.core.domain.CostingTypeDetails;
import co.com.cybersoft.maintenance.tables.core.domain.StoreTypeDetails;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class WarehouseDetails {
	
	private Long companyId;


	private String company;


	private Integer code;


	private String storeName;


	private Long physicalLocationId;


	private String physicalLocation;


	private Long costingTypeId;


	private String costingType;


	private String comment;


	private Long storeTypeId;


	private String storeType;


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
	public Long getPhysicalLocationId() {
		return physicalLocationId;	
	}
		
	public void setPhysicalLocationId(Long physicalLocationId) {
		this.physicalLocationId = physicalLocationId;	
	}
	public String getPhysicalLocation() {
		return physicalLocation;	
	}
		
	public void setPhysicalLocation(String physicalLocation) {
		this.physicalLocation = physicalLocation;	
	}
	public Long getCostingTypeId() {
		return costingTypeId;	
	}
		
	public void setCostingTypeId(Long costingTypeId) {
		this.costingTypeId = costingTypeId;	
	}
	public String getCostingType() {
		return costingType;	
	}
		
	public void setCostingType(String costingType) {
		this.costingType = costingType;	
	}
	public String getComment() {
		return comment;	
	}
		
	public void setComment(String comment) {
		this.comment = comment;	
	}
	public Long getStoreTypeId() {
		return storeTypeId;	
	}
		
	public void setStoreTypeId(Long storeTypeId) {
		this.storeTypeId = storeTypeId;	
	}
	public String getStoreType() {
		return storeType;	
	}
		
	public void setStoreType(String storeType) {
		this.storeType = storeType;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public WarehouseDetails toWarehouseDetails(Warehouse entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String _embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method _method = WarehouseDetails.class.getMethod("get"+embeddedField.getName());
				String _invoke = (String) _method.invoke(this);
				_embedded+=" - "+_invoke;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.company=entity.getCompany().getCompanyName()+_embedded;
		this.companyId=entity.getCompany().getId();
		this.storeName=storeName+_embedded;
		this.physicalLocation=entity.getPhysicalLocation().getNamePhysicalLocation()+_embedded;
		this.physicalLocationId=entity.getPhysicalLocation().getId();
		this.costingType=entity.getCostingType().getCostingType()+_embedded;
		this.costingTypeId=entity.getCostingType().getId();
		this.comment=comment+_embedded;
		this.storeType=entity.getStoreType().getStoreName()+_embedded;
		this.storeTypeId=entity.getStoreType().getId();

		return this;
	}
}