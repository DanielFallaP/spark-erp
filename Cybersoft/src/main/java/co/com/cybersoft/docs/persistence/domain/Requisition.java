package co.com.cybersoft.docs.persistence.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import co.com.cybersoft.docs.web.domain.requisition.RequisitionInfo;
import co.com.cybersoft.docs.web.domain.requisition.RequisitionItemInfo;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Document(collection="requisition")
public class Requisition {

	@Id
	private String id;
	
	@Field(value="_numericId")
	private Long numericId;
	
//	@Indexed(unique=true)
	private Integer consecutive;
	
	private List<RequisitionItem> requisitionItemEntityList=new ArrayList<RequisitionItem>();

	private Date date;

	private Boolean stock;

	private String country;

	private String state;

	private String populatedPlace;

	private Date requiredOnDate;

	private String priority;

	private String requestingDepartment;

	private String expenseType;

	private String transportationType;

	private String receivingWarehouse;

	private Boolean active;


	private Date dateOfModification;
	
	private Date dateOfCreation;
	
	private String userName;
	
	private String createdBy;
	
	public Long getNumericId() {
		return numericId;
	}
	public void setNumericId(Long numericId) {
		this.numericId = numericId;
	}
	public List<RequisitionItem> getRequisitionItemEntityList() {
		return requisitionItemEntityList;
	}
	public void setRequisitionItemEntityList(
			List<RequisitionItem> requisitionItemEntityList) {
		this.requisitionItemEntityList = requisitionItemEntityList;
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
	public String getCountry() {
		return country;	
	}
		
	public void setCountry(String country) {
		this.country = country;	
	}
	public String getState() {
		return state;	
	}
		
	public void setState(String state) {
		this.state = state;	
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
	public String getPriority() {
		return priority;	
	}
		
	public void setPriority(String priority) {
		this.priority = priority;	
	}
	public String getRequestingDepartment() {
		return requestingDepartment;	
	}
		
	public void setRequestingDepartment(String requestingDepartment) {
		this.requestingDepartment = requestingDepartment;	
	}
	public String getExpenseType() {
		return expenseType;	
	}
		
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;	
	}
	public String getTransportationType() {
		return transportationType;	
	}
		
	public void setTransportationType(String transportationType) {
		this.transportationType = transportationType;	
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

	
	public Requisition fromRequisitionInfo(RequisitionInfo webInfo){
		BeanUtils.copyProperties(webInfo, this);
		ArrayList<RequisitionItem> bodyList = new ArrayList<RequisitionItem>();
		List<RequisitionItemInfo> requisitionItemList = webInfo.getRequisitionItemList();
		for (RequisitionItemInfo requisitionItemInfo : requisitionItemList) {
			RequisitionItem requisitionItem = new RequisitionItem();
			BeanUtils.copyProperties(requisitionItemInfo, requisitionItem);
			bodyList.add(requisitionItem);
			this.setRequisitionItemEntityList(bodyList);
		}
		return this;
	}

}