package co.com.cybersoft.$module$.tables.persistence.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


$imports$
import co.com.cybersoft.$module$.tables.core.domain.$coreDomainClass$;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
$compoundIndex$
@Entity
public class $entityName$ {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
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
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
		
		$permissions$

		$references$		
		return this;
	}

}
