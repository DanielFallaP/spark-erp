package co.com.cybersoft.core.domain;

import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CompanyDetails {
	
	private Integer code;


	private String name;


	private String letter;


	private String nit;


	private String corporation;


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
	public String getName() {
		return name;	
	}
		
	public void setName(String name) {
		this.name = name;	
	}
	public String getLetter() {
		return letter;	
	}
		
	public void setLetter(String letter) {
		this.letter = letter;	
	}
	public String getNit() {
		return nit;	
	}
		
	public void setNit(String nit) {
		this.nit = nit;	
	}
	public String getCorporation() {
		return corporation;	
	}
		
	public void setCorporation(String corporation) {
		this.corporation = corporation;	
	}
	public String getActive() {
		return active;	
	}
		
	public void setActive(String active) {
		this.active = active;	
	}

	
}