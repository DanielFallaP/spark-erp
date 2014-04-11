package co.com.cybersoft.persistence.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import co.com.cybersoft.core.domain.PaymentTypeDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Document(collection="paymentType")
public class PaymentType {

	@Id
	private String id;
	
	@Indexed(unique=true)
	private String code;

	@Indexed(unique=true)
	private String description;

	private Date dateOfModification;
	
	private String userName;
	
	public Date getDateOfModification() {
		return dateOfModification;
	}
	public void setDateOfModification(Date dateOfModification) {
		this.dateOfModification = dateOfModification;
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
	
	
	public String getCode() {
		return code;	
	}
		
	public void setCode(String code) {
		this.code = code;	
	}
	public String getDescription() {
		return description;	
	}
		
	public void setDescription(String description) {
		this.description = description;	
	}

	
	public static PaymentType fromPaymentTypeDetails(PaymentTypeDetails details){
		PaymentType paymentType = new PaymentType();
		BeanUtils.copyProperties(details, paymentType);
		return paymentType;
	}
	
	public PaymentTypeDetails toPaymentTypeDetails(){
		PaymentTypeDetails details = new PaymentTypeDetails();
		BeanUtils.copyProperties(this, details);
		return details;
	}

}