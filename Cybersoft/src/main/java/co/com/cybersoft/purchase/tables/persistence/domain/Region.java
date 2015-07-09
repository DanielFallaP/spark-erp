package co.com.cybersoft.purchase.tables.persistence.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import co.com.cybersoft.purchase.tables.core.domain.RegionDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Entity
public class Region {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	private String region;
	
	private Boolean active;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="CONTINENT_ID", nullable=false)
	private Continent continent;
	
	private Date dateOfModification;
	
	private Date dateOfCreation;
	
	private String userName;
	
	private String createdBy;
	
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

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Transactional
	public Continent getContinent() {
		return continent;
	}
	public void setContinent(Continent continent) {
		this.continent = continent;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	
	public Region fromRegionDetails(RegionDetails details){
		BeanUtils.copyProperties(details, this);
		Continent continent = new Continent();
		continent.setId(details.getContinentId());
		this.continent=continent;
		return this;
	}

}