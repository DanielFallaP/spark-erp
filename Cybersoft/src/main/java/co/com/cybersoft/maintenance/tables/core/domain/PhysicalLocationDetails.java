package co.com.cybersoft.maintenance.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.maintenance.tables.persistence.domain.PhysicalLocation;
import co.com.cybersoft.util.EmbeddedField;
import java.lang.reflect.Method;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;
import co.com.cybersoft.maintenance.tables.core.domain.MeasurementUnitDetails;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class PhysicalLocationDetails {
	
	private Long companyId;


	private String company;


	private String namePhysicalLocation;


	private String description;


	private String descriptionEnglish;


	private String descriptionShort;


	private Double physicalLocationArea;


	private Long measurementUnitId;


	private String measurementUnit;


	private Long capacityPhysicalLocationId;


	private String capacityPhysicalLocation;


	private Boolean statePhysicalLocation;


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
	public String getNamePhysicalLocation() {
		return namePhysicalLocation;	
	}
		
	public void setNamePhysicalLocation(String namePhysicalLocation) {
		this.namePhysicalLocation = namePhysicalLocation;	
	}
	public String getDescription() {
		return description;	
	}
		
	public void setDescription(String description) {
		this.description = description;	
	}
	public String getDescriptionEnglish() {
		return descriptionEnglish;	
	}
		
	public void setDescriptionEnglish(String descriptionEnglish) {
		this.descriptionEnglish = descriptionEnglish;	
	}
	public String getDescriptionShort() {
		return descriptionShort;	
	}
		
	public void setDescriptionShort(String descriptionShort) {
		this.descriptionShort = descriptionShort;	
	}
	public Double getPhysicalLocationArea() {
		return physicalLocationArea;	
	}
		
	public void setPhysicalLocationArea(Double physicalLocationArea) {
		this.physicalLocationArea = physicalLocationArea;	
	}
	public Long getMeasurementUnitId() {
		return measurementUnitId;	
	}
		
	public void setMeasurementUnitId(Long measurementUnitId) {
		this.measurementUnitId = measurementUnitId;	
	}
	public String getMeasurementUnit() {
		return measurementUnit;	
	}
		
	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;	
	}
	public Long getCapacityPhysicalLocationId() {
		return capacityPhysicalLocationId;	
	}
		
	public void setCapacityPhysicalLocationId(Long capacityPhysicalLocationId) {
		this.capacityPhysicalLocationId = capacityPhysicalLocationId;	
	}
	public String getCapacityPhysicalLocation() {
		return capacityPhysicalLocation;	
	}
		
	public void setCapacityPhysicalLocation(String capacityPhysicalLocation) {
		this.capacityPhysicalLocation = capacityPhysicalLocation;	
	}
	public Boolean getStatePhysicalLocation() {
		return statePhysicalLocation;	
	}
		
	public void setStatePhysicalLocation(Boolean statePhysicalLocation) {
		this.statePhysicalLocation = statePhysicalLocation;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public PhysicalLocationDetails toPhysicalLocationDetails(PhysicalLocation entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String _embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method _method = PhysicalLocationDetails.class.getMethod("get"+embeddedField.getName());
				String _invoke = (String) _method.invoke(this);
				_embedded+=" - "+_invoke;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.company=entity.getCompany().getCompanyName()+_embedded;
		this.companyId=entity.getCompany().getId();
		this.namePhysicalLocation=namePhysicalLocation+_embedded;
		this.description=description+_embedded;
		this.descriptionEnglish=descriptionEnglish+_embedded;
		this.descriptionShort=descriptionShort+_embedded;
		this.measurementUnit=entity.getMeasurementUnit().getNameUnit()+_embedded;
		this.measurementUnitId=entity.getMeasurementUnit().getId();
		this.capacityPhysicalLocation=entity.getCapacityPhysicalLocation().getNameUnit()+_embedded;
		this.capacityPhysicalLocationId=entity.getCapacityPhysicalLocation().getId();

		return this;
	}
}