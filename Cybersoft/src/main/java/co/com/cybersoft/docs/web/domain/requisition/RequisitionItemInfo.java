package co.com.cybersoft.docs.web.domain.requisition;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import co.com.cybersoft.tables.core.domain.PriorityDetails;


/**
 * Controller for requisitionItem
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class RequisitionItemInfo implements Serializable{
	
	private Boolean created;
	
	private String submit;
	
	private String calledFrom;
	
	private String id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	@NotEmpty
	private String item;
	
	private Boolean selected;

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
	
	private RequisitionItemInfo requisitionItemModificationInfo;
	
	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public RequisitionItemInfo getRequisitionItemModificationInfo() {
		return requisitionItemModificationInfo;
	}

	public void setRequisitionItemModificationInfo(
			RequisitionItemInfo requisitionItemModificationInfo) {
		this.requisitionItemModificationInfo = requisitionItemModificationInfo;
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
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
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