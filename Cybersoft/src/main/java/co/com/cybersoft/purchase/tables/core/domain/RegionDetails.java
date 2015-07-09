package co.com.cybersoft.purchase.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import co.com.cybersoft.purchase.tables.persistence.domain.Region;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RegionDetails {
	
	private String continent;

	private Long continentId;

	private String region;

	private Boolean active;

		
	private Date dateOfModification;
	
	private Long id;
	
	private String userName;
	
	private Date dateOfCreation;
	
	private String createdBy;
	
	public Long getContinentId() {
		return continentId;
	}

	public void setContinentId(Long continentId) {
		this.continentId = continentId;
	}
	
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
	
	public String getContinent() {
		return continent;	
	}
		
	public void setContinent(String continent) {
		this.continent = continent;	
	}
	public String getRegion() {
		return region;	
	}
		
	public void setRegion(String region) {
		this.region = region;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public RegionDetails toRegionDetails(Region entity){
		BeanUtils.copyProperties(entity, this);
		this.continent=entity.getContinent().getContinent();
		return this;
	}
}