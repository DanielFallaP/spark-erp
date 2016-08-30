package co.com.cybersoft.purchase.tables.web.domain.region;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.NotEmpty;
import co.com.cybersoft.purchase.tables.core.domain.ContinentDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for region
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class RegionInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private Long id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	private Long continentId;


	private String continent;


	private List<ContinentDetails> continentList;
	@NotEmpty
	private String region;


	private Boolean active;


	public List<ContinentDetails> getContinentList() {
		return continentList;
	}
	public void setContinentList(
				List<ContinentDetails> continentList) {
			this.continentList = continentList;
	}

	public String getContinent() {
		return continent;	
	}
		
	public void setContinent(String continent) {
		this.continent = continent;	
	}

	public Long getContinentId() {
		return continentId;	
	}
		
	public void setContinentId(Long continentId) {
		this.continentId = continentId;	
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