package co.com.cybersoft.core.domain;

import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class PaymentTypeDetails {
	
	private String code;


	private String description;


		
	private Date dateOfModification;
	
	private String id;
	
	private String userName;
	
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
	
	public String getCode() {
		return code;	
	}
		
	public void setCode(String code) {
		this.code = code;	
	}
	public String getDescription() {
		return description;	
	}
		
	public void setDescription(String description) {
		this.description = description;	
	}

	
}