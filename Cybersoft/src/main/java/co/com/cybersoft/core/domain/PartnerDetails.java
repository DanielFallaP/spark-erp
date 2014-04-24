package co.com.cybersoft.core.domain;

import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class PartnerDetails {
	
	private Integer code;


	private String description;


	private String nit;


	private String active;


		
	private Date dateOfModification;
	
	private String id;
	
	private String userName;
	
	private Date dateOfCreation;
	
	private String createdBy;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
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
	
	public Integer getCode() {
		return code;	
	}
		
	public void setCode(Integer code) {
		this.code = code;	
	}
	public String getDescription() {
		return description;	
	}
		
	public void setDescription(String description) {
		this.description = description;	
	}
	public String getNit() {
		return nit;	
	}
		
	public void setNit(String nit) {
		this.nit = nit;	
	}
	public String getActive() {
		return active;	
	}
		
	public void setActive(String active) {
		this.active = active;	
	}

	
}