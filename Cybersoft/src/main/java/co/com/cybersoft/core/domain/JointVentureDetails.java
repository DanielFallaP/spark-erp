package co.com.cybersoft.core.domain;

import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class JointVentureDetails {
	
	private String code;


	private Date fromDate;


	private Date toDate;


	private String bill;


	private String partner;


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
	
	public String getCode() {
		return code;	
	}
		
	public void setCode(String code) {
		this.code = code;	
	}
	public Date getFromDate() {
		return fromDate;	
	}
		
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;	
	}
	public Date getToDate() {
		return toDate;	
	}
		
	public void setToDate(Date toDate) {
		this.toDate = toDate;	
	}
	public String getBill() {
		return bill;	
	}
		
	public void setBill(String bill) {
		this.bill = bill;	
	}
	public String getPartner() {
		return partner;	
	}
		
	public void setPartner(String partner) {
		this.partner = partner;	
	}
	public String getActive() {
		return active;	
	}
		
	public void setActive(String active) {
		this.active = active;	
	}

	
}