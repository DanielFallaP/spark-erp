package co.com.cybersoft.persistence.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;

import co.com.cybersoft.events.ArticleDetails;

public class Article {

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
