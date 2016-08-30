package co.com.cybersoft.purchase.tables.web.domain.country;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.NotEmpty;
import co.com.cybersoft.purchase.tables.core.domain.RegionDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for country
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CountryInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private Long id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	private Long regionId;


	private String region;


	private List<RegionDetails> regionList;
	@NotEmpty
	private String country;


	@NotEmpty
	private String localName;


	@NotEmpty
	private String frenchName;


	private Boolean active;


	public List<RegionDetails> getRegionList() {
		return regionList;
	}
	public void setRegionList(
				List<RegionDetails> regionList) {
			this.regionList = regionList;
	}

	public String getRegion() {
		return region;	
	}
		
	public void setRegion(String region) {
		this.region = region;	
	}

	public Long getRegionId() {
		return regionId;	
	}
		
	public void setRegionId(Long regionId) {
		this.regionId = regionId;	
	}

	public String getCountry() {
		return country;	
	}
		
	public void setCountry(String country) {
		this.country = country;	
	}

	public String getLocalName() {
		return localName;	
	}
		
	public void setLocalName(String localName) {
		this.localName = localName;	
	}

	public String getFrenchName() {
		return frenchName;	
	}
		
	public void setFrenchName(String frenchName) {
		this.frenchName = frenchName;	
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