package co.com.cybersoft.docs.web.domain.requisition;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import co.com.cybersoft.core.domain.ItemDetails;
import co.com.cybersoft.core.domain.PriorityDetails;

/**
 * Controller for requisitionItem
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class RequisitionItemModificationInfo implements Serializable{

	
	
	@NotEmpty
	private String item;


	private List<ItemDetails> itemList;
	private Date bodyRequiredOnDate;


	@NotEmpty
	private String priority;


	private List<PriorityDetails> priorityList;
	@NotNull
	private Double quantity;


	@NotNull
	private Double localCurrencyUnitValue;


	@NotNull
	private Double foreignCurrencyUnitValue;


	private Boolean active;


	public List<ItemDetails> getItemList() {
		return itemList;
	}

	public void setItemList(
				List<ItemDetails> itemList) {
			this.itemList = itemList;
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

	public List<PriorityDetails> getPriorityList() {
		return priorityList;
	}

	public void setPriorityList(
				List<PriorityDetails> priorityList) {
			this.priorityList = priorityList;
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
