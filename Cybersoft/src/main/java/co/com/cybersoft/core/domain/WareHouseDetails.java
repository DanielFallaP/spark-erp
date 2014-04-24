package co.com.cybersoft.core.domain;

import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class WareHouseDetails {
	
	private String code;


	private String description;


	private String accountCode;


	private String accountConceptCode;


	private String calculusType;


	private String operationType;


	private String afe;


	private String contract;


	private String active;


		
	private Date dateOfModification;
	
	private String id;
	
	private String userName;
	
	private Date dateOfCreation;
	
	private String createdBy;
	
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
	public String getDescription() {
		return description;	
	}
		
	public void setDescription(String description) {
		this.description = description;	
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

	
}