package co.com.cybersoft.web.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ArticleInfo implements Serializable{

	@NotNull
	@NotEmpty
	private String code;

//	private String shortDescription;
//
//	@NotNull
//	@NotEmpty
//	private String purchaseUnitOfMeasurement;
//	
//	private String partNumber;
//	
//	private String array;
//	
//	private String groupOfItems;
//	
//	private Boolean active;
//	
//	private Boolean blocked;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

//	public String getShortDescription() {
//		return shortDescription;
//	}
//
//	public void setShortDescription(String shortDescription) {
//		this.shortDescription = shortDescription;
//	}
//
//	public String getPurchaseUnitOfMeasurement() {
//		return purchaseUnitOfMeasurement;
//	}
//
//	public void setPurchaseUnitOfMeasurement(String purchaseUnitOfMeasurement) {
//		this.purchaseUnitOfMeasurement = purchaseUnitOfMeasurement;
//	}
//
//	public String getPartNumber() {
//		return partNumber;
//	}
//
//	public void setPartNumber(String partNumber) {
//		this.partNumber = partNumber;
//	}
//
//	public String getArray() {
//		return array;
//	}
//
//	public void setArray(String array) {
//		this.array = array;
//	}
//
//	public String getGroupOfItems() {
//		return groupOfItems;
//	}
//
//	public void setGroupOfItems(String groupOfItems) {
//		this.groupOfItems = groupOfItems;
//	}
//
//	public Boolean getActive() {
//		return active;
//	}
//
//	public void setActive(Boolean active) {
//		this.active = active;
//	}
//
//	public Boolean getBlocked() {
//		return blocked;
//	}
//
//	public void setBlocked(Boolean blocked) {
//		this.blocked = blocked;
//	}
	
	
		
}
