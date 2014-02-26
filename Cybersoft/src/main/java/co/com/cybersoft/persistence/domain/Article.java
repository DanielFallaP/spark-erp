package co.com.cybersoft.persistence.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import co.com.cybersoft.events.ArticleDetails;

public class Article {

	private String id;
	private String code;
//	private String shortDescription;
//	private String purchaseUnitOfMeasurement;
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
	
//	public String getShortDescription() {
//		return shortDescription;
//	}
//	public void setShortDescription(String shortDescription) {
//		this.shortDescription = shortDescription;
//	}
//	public String getPurchaseUnitOfMeasurement() {
//		return purchaseUnitOfMeasurement;
//	}
//	public void setPurchaseUnitOfMeasurement(String purchaseUnitOfMeasurement) {
//		this.purchaseUnitOfMeasurement = purchaseUnitOfMeasurement;
//	}
//	public Date getDateOfCreation() {
//		return dateOfCreation;
//	}
//	public void setDateOfCreation(Date dateOfCreation) {
//		this.dateOfCreation = dateOfCreation;
//	}
//	public String getPartNumber() {
//		return partNumber;
//	}
//	public void setPartNumber(String partNumber) {
//		this.partNumber = partNumber;
//	}
//	public String getArray() {
//		return array;
//	}
//	public void setArray(String array) {
//		this.array = array;
//	}
//	public String getGroupOfItems() {
//		return groupOfItems;
//	}
//	public void setGroupOfItems(String groupOfItems) {
//		this.groupOfItems = groupOfItems;
//	}
//	public boolean isActive() {
//		return active;
//	}
//	public void setActive(boolean active) {
//		this.active = active;
//	}
//	public boolean isBlocked() {
//		return blocked;
//	}
//	public void setBlocked(boolean blocked) {
//		this.blocked = blocked;
//	}
	
	public static Article fromArticleDetails(ArticleDetails articleDetails){
		Article article = new Article();
		//article.setDateOfCreation(articleDetails.getDateOfCreation());
		BeanUtils.copyProperties(articleDetails, article);
		return article;
	}
	
	public ArticleDetails toArticleDetails(){
		ArticleDetails details = new ArticleDetails();
		BeanUtils.copyProperties(this, details);
		return details;
	}
	
}
