package co.com.cybersoft.persistence.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import co.com.cybersoft.core.domain.$coreDomainClass$;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Document(collection="$variableName$")
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
	
	public static $entityName$ from$coreDomainClass$($coreDomainClass$ details){
		$entityName$ $variableName$ = new $entityName$();
		BeanUtils.copyProperties(details, $variableName$);
		return $variableName$;
	}
	
	public $coreDomainClass$ to$coreDomainClass$(){
		$coreDomainClass$ details = new $coreDomainClass$();
		BeanUtils.copyProperties(this, details);
		return details;
	}

}
