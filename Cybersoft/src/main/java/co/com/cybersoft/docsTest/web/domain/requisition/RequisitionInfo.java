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

	private String calledFrom;
	
	private String id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	private Date dateOfModification;
	
	private List<RequisitionItemInfo> requisitionItemList=new ArrayList<RequisitionItemInfo>();
	
	private RequisitionItemInfo currentRequisitionItemInfo;
	
	private RequisitionItemInfo requisitionItemModificationInfo;
	
	private String deletion;
	
	
	public Boolean get_toSave() {
		return _toSave;
	}

	public void set_toSave(Boolean _toSave) {
		this._toSave = _toSave;
	}

	public Boolean getShowBody() {
		return showBody;
	}

	public void setShowBody(Boolean showBody) {
		this.showBody = showBody;
	}



	@NotNull
	@Range(max=99999999)
	private Integer consecutive;


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
	
	public RequisitionInfo(){
	}
	


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
	private Boolean active;
	
	public RequisitionItemInfo getRequisitionItemModificationInfo() {
		return requisitionItemModificationInfo;
	}

	public void setRequisitionItemModificationInfo(
			RequisitionItemInfo requisitionItemModificationInfo) {
		this.requisitionItemModificationInfo = requisitionItemModificationInfo;
	}

	public List<RequisitionItemInfo> getRequisitionItemList() {
		return requisitionItemList;
	}
	
	public void setRequisitionItemList(List<RequisitionItemInfo> requisitionItemList) {
		this.requisitionItemList = requisitionItemList;
	}
	
	public Long getNumericId() {
		return numericId;
	}

	public void setNumericId(Long numericId) {
		this.numericId = numericId;
	}
	
	public String getDeletion() {
		return deletion;
	}

	public void setDeletion(String deletion) {
		this.deletion = deletion;
	}

	public RequisitionItemInfo getCurrentRequisitionItemInfo() {
		return currentRequisitionItemInfo;
	}

	public void setCurrentRequisitionItemInfo(
			RequisitionItemInfo currentRequisitionItemInfo) {
		this.currentRequisitionItemInfo = currentRequisitionItemInfo;
	}
	
	public Date getDateOfModification() {
		return dateOfModification;
	}

	public void setDateOfModification(Date dateOfModification) {
		this.dateOfModification = dateOfModification;
	}

	public Integer getConsecutive() {
		return consecutive;	
	}
		
	public void setConsecutive(Integer consecutive) {
		this.consecutive = consecutive;	
	}

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

	public List<CountryDetails> getCountryList() {
		return countryList;
	}

	public void setCountryList(
				List<CountryDetails> countryList) {
			this.countryList = countryList;
	}

	public String getCountry() {
		return country;	
	}
		
	public void setCountry(String country) {
		this.country = country;	
	}

	public List<StateDetails> getStateList() {
		return stateList;
	}

	public void setStateList(
				List<StateDetails> stateList) {
			this.stateList = stateList;
	}

	public String getState() {
		return state;	
	}
		
	public void setState(String state) {
		this.state = state;	
	}

	public List<PopulatedPlaceDetails> getPopulatedPlaceList() {
		return populatedPlaceList;
	}

	public void setPopulatedPlaceList(
				List<PopulatedPlaceDetails> populatedPlaceList) {
			this.populatedPlaceList = populatedPlaceList;
	}

	public String getPopulatedPlace() {
		return populatedPlace;	
	}
		
	public void setPopulatedPlace(String populatedPlace) {
		this.populatedPlace = populatedPlace;	
	}

	public Date getRequiredOnDate() {
		return requiredOnDate;	
	}
		
	public void setRequiredOnDate(Date requiredOnDate) {
		this.requiredOnDate = requiredOnDate;	
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

	public List<DepartmentDetails> getRequestingDepartmentList() {
		return requestingDepartmentList;
	}

	public void setRequestingDepartmentList(
				List<DepartmentDetails> requestingDepartmentList) {
			this.requestingDepartmentList = requestingDepartmentList;
	}

	public String getRequestingDepartment() {
		return requestingDepartment;	
	}
		
	public void setRequestingDepartment(String requestingDepartment) {
		this.requestingDepartment = requestingDepartment;	
	}

	public List<ExpenseTypeDetails> getExpenseTypeList() {
		return expenseTypeList;
	}

	public void setExpenseTypeList(
				List<ExpenseTypeDetails> expenseTypeList) {
			this.expenseTypeList = expenseTypeList;
	}

	public String getExpenseType() {
		return expenseType;	
	}
		
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;	
	}

	public List<TransportationTypeDetails> getTransportationTypeList() {
		return transportationTypeList;
	}

	public void setTransportationTypeList(
				List<TransportationTypeDetails> transportationTypeList) {
			this.transportationTypeList = transportationTypeList;
	}

	public String getTransportationType() {
		return transportationType;	
	}
		
	public void setTransportationType(String transportationType) {
		this.transportationType = transportationType;	
	}

	public List<WarehouseDetails> getReceivingWarehouseList() {
		return receivingWarehouseList;
	}

	public void setReceivingWarehouseList(
				List<WarehouseDetails> receivingWarehouseList) {
			this.receivingWarehouseList = receivingWarehouseList;
	}

	public String getReceivingWarehouse() {
		return receivingWarehouse;	
	}
		
	public void setReceivingWarehouse(String receivingWarehouse) {
		this.receivingWarehouse = receivingWarehouse;	
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