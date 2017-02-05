package co.com.cybersoft.maintenance.tables.web.domain.physicalLocation;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;
import co.com.cybersoft.maintenance.tables.core.domain.MeasurementUnitDetails;
import co.com.cybersoft.maintenance.tables.core.domain.MeasurementUnitDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for physicalLocation
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class PhysicalLocationInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private Long id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	private Long companyId;


	private String company;


	private List<CompanyDetails> companyList;
	@Length(max=50)
	@NotEmpty
	private String namePhysicalLocation;


	@Length(max=100)
	@NotEmpty
	private String description;


	@Length(max=100)
	@NotEmpty
	private String descriptionEnglish;


	@Length(max=40)
	@NotEmpty
	private String descriptionShort;


	@NotNull
	private Double physicalLocationArea;


	private Long measurementUnitId;


	private String measurementUnit;


	private List<MeasurementUnitDetails> measurementUnitList;
	private Long capacityPhysicalLocationId;


	private String capacityPhysicalLocation;


	private List<MeasurementUnitDetails> capacityPhysicalLocationList;
	private Boolean statePhysicalLocation;


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

	public List<MeasurementUnitDetails> getMeasurementUnitList() {
		return measurementUnitList;
	}
	public void setMeasurementUnitList(
				List<MeasurementUnitDetails> measurementUnitList) {
			this.measurementUnitList = measurementUnitList;
	}

	public String getMeasurementUnit() {
		return measurementUnit;	
	}
		
	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;	
	}

	public Long getMeasurementUnitId() {
		return measurementUnitId;	
	}
		
	public void setMeasurementUnitId(Long measurementUnitId) {
		this.measurementUnitId = measurementUnitId;	
	}

	public List<MeasurementUnitDetails> getCapacityPhysicalLocationList() {
		return capacityPhysicalLocationList;
	}
	public void setCapacityPhysicalLocationList(
				List<MeasurementUnitDetails> capacityPhysicalLocationList) {
			this.capacityPhysicalLocationList = capacityPhysicalLocationList;
	}

	public String getCapacityPhysicalLocation() {
		return capacityPhysicalLocation;	
	}
		
	public void setCapacityPhysicalLocation(String capacityPhysicalLocation) {
		this.capacityPhysicalLocation = capacityPhysicalLocation;	
	}

	public Long getCapacityPhysicalLocationId() {
		return capacityPhysicalLocationId;	
	}
		
	public void setCapacityPhysicalLocationId(Long capacityPhysicalLocationId) {
		this.capacityPhysicalLocationId = capacityPhysicalLocationId;	
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