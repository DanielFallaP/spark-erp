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

import co.com.cybersoft.purchase.tables.core.domain.ExchangeRateDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Table( uniqueConstraints = { @UniqueConstraint( columnNames = { "LOCALCURRENCY_ID","FOREIGNCURRENCY_ID","f_date" } ) } )
@Entity
public class ExchangeRate {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="LOCALCURRENCY_ID" , nullable=false)
	private Currency localCurrency;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="FOREIGNCURRENCY_ID" , nullable=false)
	private Currency foreignCurrency;

	@Column(name="f_date")
	private Date date;

	private Double exchangeRate;

	private Double variation;

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
	public Date getDate() {
		return date;	
	}
		
	public void setDate(Date date) {
		this.date = date;	
	}
	public Double getExchangeRate() {
		return exchangeRate;	
	}
		
	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;	
	}
	public Double getVariation() {
		return variation;	
	}
		
	public void setVariation(Double variation) {
		this.variation = variation;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public ExchangeRate fromExchangeRateDetails(ExchangeRateDetails details){
		BeanUtils.copyProperties(details, this);
		

		Currency currency0=new Currency();currency0.setId(details.getLocalCurrencyId());this.localCurrency=currency0; 
		Currency currency1=new Currency();currency1.setId(details.getForeignCurrencyId());this.foreignCurrency=currency1; 
		
		return this;
	}

}