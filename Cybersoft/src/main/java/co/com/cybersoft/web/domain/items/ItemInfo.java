package co.com.cybersoft.web.domain.items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import co.com.cybersoft.core.domain.MeasurementUnitDetails;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ItemInfo implements Serializable{

	private String id;
	
	@NotNull
	@NotEmpty
	private String code;

	private String shortDescription;

	@NotNull
	@NotEmpty
	private String purchaseUnitOfMeasurement;
	
	private String partNumber;
	
	private String array;
	
	private String groupOfItems;
	
	private Boolean enabled;
	
	private Boolean blocked;
	
	private String userName;
	
	private Boolean itemCreated;
	
	private String calledFrom;
	
	private Date activationDate;	
	
	private List<MeasurementUnitDetails> measurementUnitList;
	
	public Date getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}

	public List<MeasurementUnitDetails> getMeasurementUnitList() {
		return measurementUnitList;
	}

	public void setMeasurementUnitList(
			List<MeasurementUnitDetails> measurementUnitList) {
		this.measurementUnitList = measurementUnitList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCalledFrom() {
		return calledFrom;
	}

	public void setCalledFrom(String calledFrom) {
		this.calledFrom = calledFrom;
	}

	public Boolean getItemCreated() {
		return itemCreated;
	}

	public void setItemCreated(Boolean itemCreated) {
		this.itemCreated = itemCreated;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getPurchaseUnitOfMeasurement() {
		return purchaseUnitOfMeasurement;
	}

	public void setPurchaseUnitOfMeasurement(String purchaseUnitOfMeasurement) {
		this.purchaseUnitOfMeasurement = purchaseUnitOfMeasurement;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getArray() {
		return array;
	}

	public void setArray(String array) {
		this.array = array;
	}

	public String getGroupOfItems() {
		return groupOfItems;
	}

	public void setGroupOfItems(String groupOfItems) {
		this.groupOfItems = groupOfItems;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getBlocked() {
		return blocked;
	}

	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void rearrangeMeasurementDetailsList(String selected){
		MeasurementUnitDetails selectedMeasurementDetails=null;
		ArrayList<MeasurementUnitDetails> newList = new ArrayList<MeasurementUnitDetails>();
		for(MeasurementUnitDetails measurementUnit:measurementUnitList){
			if (measurementUnit.getName().equals(selected)){
				selectedMeasurementDetails=measurementUnit;
				newList.add(0, selectedMeasurementDetails);
			}
			else{
				newList.add(measurementUnit);
			}
		}
		measurementUnitList=newList;
	
	}
	
}
