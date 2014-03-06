package co.com.cybersoft.persistence.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import co.com.cybersoft.events.items.ItemDetails;

@Document(collection="items")
public class Item {

	@Id
	private String id;
	
	@Field("code")
	@Indexed
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
	private Date dateOfCreation;
	
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
	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
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
