package co.com.cybersoft.tables.persistence.domain;

import java.util.Date;

import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

$imports$
import co.com.cybersoft.tables.core.domain.$coreDomainClass$;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Document(collection="$variableName$")
$compoundIndex$
public class $entityName$ {

	@Id
	private String id;
	
	$fieldDeclaration$
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
	
	$gettersAndSetters$
	
	public $entityName$ from$coreDomainClass$($coreDomainClass$ details){
		BeanUtils.copyProperties(details, this);
		$embeddedReferences$
		
		return this;
	}

}
