package co.com.cybersoft.persistence.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import co.com.cybersoft.core.domain.WareHouseDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Document(collection="wareHouse")
public class WareHouse {

	@Id
	private String id;
	
	@Indexed(unique=true)
	private String code;

	@Indexed(unique=true)
	private String name;

	private String accountCode;

	private String accountConceptCode;

	private String calculusType;

	private String operationType;

	private String afe;

	private String contract;

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
	
	public String getCode() {
		return code;	
	}
		
	public void setCode(String code) {
		this.code = code;	
	}
	public String getName() {
		return name;	
	}
		
	public void setName(String name) {
		this.name = name;	
	}
	public String getAccountCode() {
		return accountCode;	
	}
		
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;	
	}
	public String getAccountConceptCode() {
		return accountConceptCode;	
	}
		
	public void setAccountConceptCode(String accountConceptCode) {
		this.accountConceptCode = accountConceptCode;	
	}
	public String getCalculusType() {
		return calculusType;	
	}
		
	public void setCalculusType(String calculusType) {
		this.calculusType = calculusType;	
	}
	public String getOperationType() {
		return operationType;	
	}
		
	public void setOperationType(String operationType) {
		this.operationType = operationType;	
	}
	public String getAfe() {
		return afe;	
	}
		
	public void setAfe(String afe) {
		this.afe = afe;	
	}
	public String getContract() {
		return contract;	
	}
		
	public void setContract(String contract) {
		this.contract = contract;	
	}
	public String getActive() {
		return active;	
	}
		
	public void setActive(String active) {
		this.active = active;	
	}

	
	public static WareHouse fromWareHouseDetails(WareHouseDetails details){
		WareHouse wareHouse = new WareHouse();
		BeanUtils.copyProperties(details, wareHouse);
		return wareHouse;
	}
	
	public WareHouseDetails toWareHouseDetails(){
		WareHouseDetails details = new WareHouseDetails();
		BeanUtils.copyProperties(this, details);
		return details;
	}

}