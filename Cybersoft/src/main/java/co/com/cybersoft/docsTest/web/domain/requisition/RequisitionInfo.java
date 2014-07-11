package co.com.cybersoft.docsTest.web.domain.requisition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import co.com.cybersoft.docsTest.persistence.domain.Requisition;
import co.com.cybersoft.docsTest.persistence.domain.RequisitionItem;
import co.com.cybersoft.tables.core.domain.CountryDetails;
import co.com.cybersoft.tables.core.domain.DepartmentDetails;
import co.com.cybersoft.tables.core.domain.ExpenseTypeDetails;
import co.com.cybersoft.tables.core.domain.PopulatedPlaceDetails;
import co.com.cybersoft.tables.core.domain.PriorityDetails;
import co.com.cybersoft.tables.core.domain.StateDetails;
import co.com.cybersoft.tables.core.domain.TransportationTypeDetails;
import co.com.cybersoft.tables.core.domain.WarehouseDetails;


/**
 * Controller for requisition
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class RequisitionInfo implements Serializable{
	
	private Long numericId;
	
	private Boolean created=Boolean.FALSE;
	
	private Boolean showBody=Boolean.FALSE;
	
	private Boolean _toSave=Boolean.FALSE;

	private String id;
	
	private String userName;

	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public Boolean getStock() {
		return stock;
	}



	public void setStock(Boolean stock) {
		this.stock = stock;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public List<CountryDetails> getCountryList() {
		return countryList;
	}



	public void setCountryList(List<CountryDetails> countryList) {
		this.countryList = countryList;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public List<StateDetails> getStateList() {
		return stateList;
	}



	public void setStateList(List<StateDetails> stateList) {
		this.stateList = stateList;
	}



	public String getPopulatedPlace() {
		return populatedPlace;
	}



	public void setPopulatedPlace(String populatedPlace) {
		this.populatedPlace = populatedPlace;
	}



	public List<PopulatedPlaceDetails> getPopulatedPlaceList() {
		return populatedPlaceList;
	}



	public void setPopulatedPlaceList(List<PopulatedPlaceDetails> populatedPlaceList) {
		this.populatedPlaceList = populatedPlaceList;
	}



	public Date getRequiredOnDate() {
		return requiredOnDate;
	}



	public void setRequiredOnDate(Date requiredOnDate) {
		this.requiredOnDate = requiredOnDate;
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



	public String getRequestingDepartment() {
		return requestingDepartment;
	}



	public void setRequestingDepartment(String requestingDepartment) {
		this.requestingDepartment = requestingDepartment;
	}



	public List<DepartmentDetails> getRequestingDepartmentList() {
		return requestingDepartmentList;
	}



	public void setRequestingDepartmentList(
			List<DepartmentDetails> requestingDepartmentList) {
		this.requestingDepartmentList = requestingDepartmentList;
	}



	public String getExpenseType() {
		return expenseType;
	}



	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}



	public List<ExpenseTypeDetails> getExpenseTypeList() {
		return expenseTypeList;
	}



	public void setExpenseTypeList(List<ExpenseTypeDetails> expenseTypeList) {
		this.expenseTypeList = expenseTypeList;
	}



	public String getTransportationType() {
		return transportationType;
	}



	public void setTransportationType(String transportationType) {
		this.transportationType = transportationType;
	}



	public List<TransportationTypeDetails> getTransportationTypeList() {
		return transportationTypeList;
	}



	public void setTransportationTypeList(
			List<TransportationTypeDetails> transportationTypeList) {
		this.transportationTypeList = transportationTypeList;
	}



	public String getReceivingWarehouse() {
		return receivingWarehouse;
	}



	public void setReceivingWarehouse(String receivingWarehouse) {
		this.receivingWarehouse = receivingWarehouse;
	}



	public List<WarehouseDetails> getReceivingWarehouseList() {
		return receivingWarehouseList;
	}



	public void setReceivingWarehouseList(
			List<WarehouseDetails> receivingWarehouseList) {
		this.receivingWarehouseList = receivingWarehouseList;
	}



	private Date dateOfCreation;
	
	private String createdBy;
	
	private Date dateOfModification;
	
	private List<RequisitionItemInfo> requisitionItemList=new ArrayList<RequisitionItemInfo>();
	
	private RequisitionItemInfo currentRequisitionItemInfo;
	
	private RequisitionItemInfo requisitionItemModificationInfo;
	
	private String deletion;

	private Boolean active;

	private Date date;


	private Boolean stock;


	@NotEmpty
	private String country;


	private List<CountryDetails> countryList;
	@NotEmpty
	private String state;


	private List<StateDetails> stateList;
	@NotEmpty
	private String populatedPlace;


	private List<PopulatedPlaceDetails> populatedPlaceList;
	private Date requiredOnDate;
	
	


	@NotEmpty
	private String priority;


	private List<PriorityDetails> priorityList;
	@NotEmpty
	private String requestingDepartment;


	private List<DepartmentDetails> requestingDepartmentList;
	@NotEmpty
	private String expenseType;


	private List<ExpenseTypeDetails> expenseTypeList;
	@NotEmpty
	private String transportationType;


	private List<TransportationTypeDetails> transportationTypeList;
	@NotEmpty
	private String receivingWarehouse;


	private List<WarehouseDetails> receivingWarehouseList;
	
	public RequisitionInfo(){
	}

	
	
	public Long getNumericId() {
		return numericId;
	}



	public void setNumericId(Long numericId) {
		this.numericId = numericId;
	}



	public Boolean getCreated() {
		return created;
	}



	public void setCreated(Boolean created) {
		this.created = created;
	}



	public Boolean getShowBody() {
		return showBody;
	}



	public void setShowBody(Boolean showBody) {
		this.showBody = showBody;
	}



	public Boolean get_toSave() {
		return _toSave;
	}



	public void set_toSave(Boolean _toSave) {
		this._toSave = _toSave;
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



	public Date getDateOfModification() {
		return dateOfModification;
	}



	public void setDateOfModification(Date dateOfModification) {
		this.dateOfModification = dateOfModification;
	}



	public List<RequisitionItemInfo> getRequisitionItemList() {
		return requisitionItemList;
	}



	public void setRequisitionItemList(List<RequisitionItemInfo> requisitionItemList) {
		this.requisitionItemList = requisitionItemList;
	}



	public RequisitionItemInfo getCurrentRequisitionItemInfo() {
		return currentRequisitionItemInfo;
	}



	public void setCurrentRequisitionItemInfo(
			RequisitionItemInfo currentRequisitionItemInfo) {
		this.currentRequisitionItemInfo = currentRequisitionItemInfo;
	}



	public RequisitionItemInfo getRequisitionItemModificationInfo() {
		return requisitionItemModificationInfo;
	}



	public void setRequisitionItemModificationInfo(
			RequisitionItemInfo requisitionItemModificationInfo) {
		this.requisitionItemModificationInfo = requisitionItemModificationInfo;
	}



	public String getDeletion() {
		return deletion;
	}



	public void setDeletion(String deletion) {
		this.deletion = deletion;
	}


	public Boolean getActive() {
		return active;
	}



	public void setActive(Boolean active) {
		this.active = active;
	}



	public RequisitionInfo toRequisitionInfo(Requisition entity){
		BeanUtils.copyProperties(entity, this);
		List<RequisitionItemInfo> bodyList = new ArrayList<RequisitionItemInfo>();
		List<RequisitionItem> requisitionItemEntityList = entity.getRequisitionItemEntityList();
		for (RequisitionItem requisitionItem : requisitionItemEntityList) {
			RequisitionItemInfo requisitionItemInfo = new RequisitionItemInfo();
			BeanUtils.copyProperties(requisitionItem, requisitionItemInfo);
			bodyList.add(requisitionItemInfo);
			this.setRequisitionItemList(bodyList);
		}
		return this;
	}
	
}