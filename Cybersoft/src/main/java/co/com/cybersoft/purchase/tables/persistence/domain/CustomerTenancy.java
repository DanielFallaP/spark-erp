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


import co.com.cybersoft.purchase.tables.persistence.domain.Currency;

import co.com.cybersoft.purchase.tables.core.domain.CustomerTenancyDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Entity
public class CustomerTenancy {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	private String softwareTradeMark;

	@Column(unique=true)
	private String softwareVersion;

	@Column(unique=true)
	private String softwareSerialNo;

	private Boolean doubleCurrency;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="LOCALCURRENCY_ID" )
	private Currency localCurrency;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="FOREIGNCURRENCY_ID" )
	private Currency foreignCurrency;

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
	
	public String getSoftwareTradeMark() {
		return softwareTradeMark;	
	}
		
	public void setSoftwareTradeMark(String softwareTradeMark) {
		this.softwareTradeMark = softwareTradeMark;	
	}
	public String getSoftwareVersion() {
		return softwareVersion;	
	}
		
	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;	
	}
	public String getSoftwareSerialNo() {
		return softwareSerialNo;	
	}
		
	public void setSoftwareSerialNo(String softwareSerialNo) {
		this.softwareSerialNo = softwareSerialNo;	
	}
	public Boolean getDoubleCurrency() {
		return doubleCurrency;	
	}
		
	public void setDoubleCurrency(Boolean doubleCurrency) {
		this.doubleCurrency = doubleCurrency;	
	}
	public Currency getLocalCurrency() {
		return localCurrency;	
	}
		
	public void setLocalCurrency(Currency localCurrency) {
		this.localCurrency = localCurrency;	
	}
	public Currency getForeignCurrency() {
		return foreignCurrency;	
	}
		
	public void setForeignCurrency(Currency foreignCurrency) {
		this.foreignCurrency = foreignCurrency;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public CustomerTenancy fromCustomerTenancyDetails(CustomerTenancyDetails details){
		BeanUtils.copyProperties(details, this);

		Currency currency0=new Currency();currency0.setId(details.getLocalCurrencyId());this.localCurrency=currency0; 
		Currency currency1=new Currency();currency1.setId(details.getForeignCurrencyId());this.foreignCurrency=currency1; 
		
		return this;
	}

}