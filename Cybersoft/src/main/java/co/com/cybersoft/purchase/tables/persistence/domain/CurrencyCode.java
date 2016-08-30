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


import co.com.cybersoft.purchase.tables.persistence.domain.Country;

import co.com.cybersoft.purchase.tables.core.domain.CurrencyCodeDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Entity
public class CurrencyCode {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	private String currencyName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="COUNTRY_ID" , nullable=false)
	private Country country;

	@Column(unique=true)
	private String currency;

	private String symbol;

	private Integer dec1;

	private Integer dec2;

	private Integer dec3;

	private String hex1;

	private String hex2;

	private String hex3;

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
	
	public String getCurrencyName() {
		return currencyName;	
	}
		
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;	
	}
	public Country getCountry() {
		return country;	
	}
		
	public void setCountry(Country country) {
		this.country = country;	
	}
	public String getCurrency() {
		return currency;	
	}
		
	public void setCurrency(String currency) {
		this.currency = currency;	
	}
	public String getSymbol() {
		return symbol;	
	}
		
	public void setSymbol(String symbol) {
		this.symbol = symbol;	
	}
	public Integer getDec1() {
		return dec1;	
	}
		
	public void setDec1(Integer dec1) {
		this.dec1 = dec1;	
	}
	public Integer getDec2() {
		return dec2;	
	}
		
	public void setDec2(Integer dec2) {
		this.dec2 = dec2;	
	}
	public Integer getDec3() {
		return dec3;	
	}
		
	public void setDec3(Integer dec3) {
		this.dec3 = dec3;	
	}
	public String getHex1() {
		return hex1;	
	}
		
	public void setHex1(String hex1) {
		this.hex1 = hex1;	
	}
	public String getHex2() {
		return hex2;	
	}
		
	public void setHex2(String hex2) {
		this.hex2 = hex2;	
	}
	public String getHex3() {
		return hex3;	
	}
		
	public void setHex3(String hex3) {
		this.hex3 = hex3;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public CurrencyCode fromCurrencyCodeDetails(CurrencyCodeDetails details){
		BeanUtils.copyProperties(details, this);

		Country country0=new Country();country0.setId(details.getCountryId());this.country=country0; 
		
		return this;
	}

}