package co.com.cybersoft.docs.persistence.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */

public class RequisitionItem {

	private String id;
	
	private String item;

	private Date bodyRequiredOnDate;

	private String priority;

	private Double quantity;

	private Double localCurrencyUnitValue;

	private Double foreignCurrencyUnitValue;

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
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getItem() {
		return item;	
	}
		
	public void setItem(String item) {
		this.item = item;	
	}

	public Date getBodyRequiredOnDate() {
		return bodyRequiredOnDate;
	}
	public void setBodyRequiredOnDate(Date bodyRequiredOnDate) {
		this.bodyRequiredOnDate = bodyRequiredOnDate;
	}
	public String getPriority() {
		return priority;	
	}
		
	public void setPriority(String priority) {
		this.priority = priority;	
	}
	public Double getQuantity() {
		return quantity;	
	}
		
	public void setQuantity(Double quantity) {
		this.quantity = quantity;	
	}
	public Double getLocalCurrencyUnitValue() {
		return localCurrencyUnitValue;	
	}
		
	public void setLocalCurrencyUnitValue(Double localCurrencyUnitValue) {
		this.localCurrencyUnitValue = localCurrencyUnitValue;	
	}
	public Double getForeignCurrencyUnitValue() {
		return foreignCurrencyUnitValue;	
	}
		
	public void setForeignCurrencyUnitValue(Double foreignCurrencyUnitValue) {
		this.foreignCurrencyUnitValue = foreignCurrencyUnitValue;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	

}