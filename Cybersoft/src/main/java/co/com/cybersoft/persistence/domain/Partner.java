package co.com.cybersoft.persistence.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import co.com.cybersoft.core.domain.PartnerDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Document(collection="partner")
public class Partner {

	@Id
	private String id;
	
	@Indexed(unique=true)
	private Integer code;

	@Indexed(unique=true)
	private String name;

	@Indexed(unique=true)
	private String nit;

	private String active;


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
	
	public Integer getCode() {
		return code;	
	}
		
	public void setCode(Integer code) {
		this.code = code;	
	}
	public String getName() {
		return name;	
	}
		
	public void setName(String name) {
		this.name = name;	
	}
	public String getNit() {
		return nit;	
	}
		
	public void setNit(String nit) {
		this.nit = nit;	
	}
	public String getActive() {
		return active;	
	}
		
	public void setActive(String active) {
		this.active = active;	
	}

	
	public static Partner fromPartnerDetails(PartnerDetails details){
		Partner partner = new Partner();
		BeanUtils.copyProperties(details, partner);
		return partner;
	}
	
	public PartnerDetails toPartnerDetails(){
		PartnerDetails details = new PartnerDetails();
		BeanUtils.copyProperties(this, details);
		return details;
	}

}