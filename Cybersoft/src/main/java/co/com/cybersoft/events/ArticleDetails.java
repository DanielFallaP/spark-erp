package co.com.cybersoft.events;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ArticleDetails {
	

	private String id;
	private String code;
	private String shortDescription;
	private String purchaseUnitOfMeasurement;
//	private String partNumber;
//	private String array;
//	private String groupOfItems;
//	private Boolean active;
//	private Boolean blocked;
//	private Date dateOfCreation;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPurchaseUnitOfMeasurement() {
		return purchaseUnitOfMeasurement;
	}
	public void setPurchaseUnitOfMeasurement(String purchaseUnitOfMeasurement) {
		this.purchaseUnitOfMeasurement = purchaseUnitOfMeasurement;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
}
