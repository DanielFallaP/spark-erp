package co.com.cybersoft.persistence.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;

import co.com.cybersoft.events.ItemDetails;

public class Item {

	private String id;
	private String code;

	private String shortDescription;
	
	@NotNull
	@NotEmpty
	private String purchaseUnitOfMeasurement;
	
//	private Date dateOfCreation;
//	private String partNumber;
//	private String array;
//	private String groupOfItems;
//	private boolean active;
//	private boolean blocked;
	
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
	public static Item fromItemDetails(ItemDetails itemDetails){
		Item item = new Item();
		//item.setDateOfCreation(itemDetails.getDateOfCreation());
		BeanUtils.copyProperties(itemDetails, item);
		return item;
	}
	
	public ItemDetails toItemDetails(){
		ItemDetails details = new ItemDetails();
		BeanUtils.copyProperties(this, details);
		return details;
	}
	
}
