package co.com.cybersoft.persistence.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import co.com.cybersoft.core.domain.JointVentureDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Document(collection="jointVenture")
public class JointVenture {

	@Id
	private String id;
	
	@Indexed(unique=true)
	private String code;

	private String bill;

	private String partner;

	private String active;

	private Date fromDate;

	private Date toDate;


	private Date dateOfModification;
	
	private Date dateOfCreation;
	
	private String userName;
	
	private String createdBy;
	
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
	
	public String getCode() {
		return code;	
	}
		
	public void setCode(String code) {
		this.code = code;	
	}
	public String getBill() {
		return bill;	
	}
		
	public void setBill(String bill) {
		this.bill = bill;	
	}
	public String getPartner() {
		return partner;	
	}
		
	public void setPartner(String partner) {
		this.partner = partner;	
	}
	public String getActive() {
		return active;	
	}
		
	public void setActive(String active) {
		this.active = active;	
	}
	public Date getFromDate() {
		return fromDate;	
	}
		
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;	
	}
	public Date getToDate() {
		return toDate;	
	}
		
	public void setToDate(Date toDate) {
		this.toDate = toDate;	
	}

	
	public static JointVenture fromJointVentureDetails(JointVentureDetails details){
		JointVenture jointVenture = new JointVenture();
		BeanUtils.copyProperties(details, jointVenture);
		return jointVenture;
	}
	
	public JointVentureDetails toJointVentureDetails(){
		JointVentureDetails details = new JointVentureDetails();
		BeanUtils.copyProperties(this, details);
		return details;
	}

}