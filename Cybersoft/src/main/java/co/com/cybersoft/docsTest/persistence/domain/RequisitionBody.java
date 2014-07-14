package co.com.cybersoft.docsTest.persistence.domain;

import java.util.Date;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */

public class RequisitionBody {

	private String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	private String item;

	private Date bodyRequiredOnDate;

	private String priority;

	private Double quantity;

	private Double localCurrencyUnitValue;

	private Double foreignCurrencyUnitValue;
	
	
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

}