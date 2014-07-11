package co.com.cybersoft.docsTest.web.domain.requisition;

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
	
	private Long numericId;
	
	private Boolean created;
	
	private String submit;
	
	private String id;
	
	private RequisitionItemInfo requisitionItemModificationInfo;

	@NotEmpty
	private String item;
	
	private Boolean selected;

	private Date bodyRequiredOnDate;

	@NotEmpty
	private String priority;


	private List<PriorityDetails> priorityList;
	@NotNull
	private Double quantity;

	private Double localCurrencyUnitValue;

	private Double foreignCurrencyUnitValue;

	public Boolean getCreated() {
		return created;
	}

	public void setCreated(Boolean created) {
		this.created = created;
	}

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Long getNumericId() {
		return numericId;
	}

	public void setNumericId(Long numericId) {
		this.numericId = numericId;
	}

	public RequisitionItemInfo getRequisitionItemModificationInfo() {
		return requisitionItemModificationInfo;
	}

	public void setRequisitionItemModificationInfo(
			RequisitionItemInfo requisitionItemModificationInfo) {
		this.requisitionItemModificationInfo = requisitionItemModificationInfo;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
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

	public List<PriorityDetails> getPriorityList() {
		return priorityList;
	}

	public void setPriorityList(List<PriorityDetails> priorityList) {
		this.priorityList = priorityList;
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