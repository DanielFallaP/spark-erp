package co.com.cybersoft.purchase.tables.persistence.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.BeanUtils;

import co.com.cybersoft.purchase.tables.core.domain.ContinentDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Entity
public class Continent {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	private String continent;

	private Boolean active;


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
	
	public String getContinent() {
		return continent;	
	}
		
	public void setContinent(String continent) {
		this.continent = continent;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public Continent fromContinentDetails(ContinentDetails details){
		BeanUtils.copyProperties(details, this);
		
		return this;
	}

}