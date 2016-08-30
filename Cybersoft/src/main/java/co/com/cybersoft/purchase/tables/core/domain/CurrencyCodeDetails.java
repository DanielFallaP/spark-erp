package co.com.cybersoft.purchase.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.purchase.tables.persistence.domain.CurrencyCode;
import co.com.cybersoft.util.EmbeddedField;
import java.lang.reflect.Method;
import co.com.cybersoft.purchase.tables.core.domain.CountryDetails;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CurrencyCodeDetails {
	
	private String currencyName;


	private Long countryId;


	private String country;


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
	
	private Long id;
	
	private String userName;
	
	private Date dateOfCreation;
	
	private String createdBy;
	
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
	
	public String getCurrencyName() {
		return currencyName;	
	}
		
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;	
	}
	public Long getCountryId() {
		return countryId;	
	}
		
	public void setCountryId(Long countryId) {
		this.countryId = countryId;	
	}
	public String getCountry() {
		return country;	
	}
		
	public void setCountry(String country) {
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

	
	public CurrencyCodeDetails toCurrencyCodeDetails(CurrencyCode entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String _embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method _method = CurrencyCodeDetails.class.getMethod("get"+embeddedField.getName());
				String _invoke = (String) _method.invoke(this);
				_embedded+=" - "+_invoke;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.currencyName=currencyName+_embedded;
		this.country=entity.getCountry().getCountry()+_embedded;
		this.countryId=entity.getCountry().getId();
		this.currency=currency+_embedded;
		this.symbol=symbol+_embedded;
		this.hex1=hex1+_embedded;
		this.hex2=hex2+_embedded;
		this.hex3=hex3+_embedded;

		return this;
	}
}