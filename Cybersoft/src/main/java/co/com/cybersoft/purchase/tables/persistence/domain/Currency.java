package co.com.cybersoft.purchase.tables.persistence.domain;

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


import co.com.cybersoft.purchase.tables.persistence.domain.CurrencyCode;

import co.com.cybersoft.purchase.tables.core.domain.CurrencyDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Entity
public class Currency {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="CODE_ID" , nullable=false)
	private CurrencyCode code;

	@Column(unique=true)
	private String currency;

	private Boolean active;


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
	
	public CurrencyCode getCode() {
		return code;	
	}
		
	public void setCode(CurrencyCode code) {
		this.code = code;	
	}
	public String getCurrency() {
		return currency;	
	}
		
	public void setCurrency(String currency) {
		this.currency = currency;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public Currency fromCurrencyDetails(CurrencyDetails details){
		BeanUtils.copyProperties(details, this);
		

		CurrencyCode currencyCode0=new CurrencyCode();currencyCode0.setId(details.getCodeId());this.code=currencyCode0; 
		
		return this;
	}

}