package co.com.cybersoft.$module$.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.$module$.tables.persistence.domain.$entityName$;
$imports$

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class $coreDomainClass$ {
	
	$fieldDeclaration$		
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
	
	$gettersAndSetters$
	
	public $entityName$Details to$entityName$Details($entityName$ entity){
		BeanUtils.copyProperties(entity, this);
		$embeddedReferences$
		return this;
	}
}
