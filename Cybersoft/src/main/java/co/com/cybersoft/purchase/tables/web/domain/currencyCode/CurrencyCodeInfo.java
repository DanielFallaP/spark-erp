package co.com.cybersoft.purchase.tables.web.domain.currencyCode;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import co.com.cybersoft.purchase.tables.core.domain.CountryDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for currencyCode
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CurrencyCodeInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private Long id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	@NotEmpty
	private String currencyName;


	private Long countryId;


	private String country;


	private List<CountryDetails> countryList;
	@Length(max=3)
	@NotEmpty
	private String currency;


	@Length(max=4)
	@NotEmpty
	private String symbol;


	@NotNull
	private Integer dec1;


	@NotNull
	private Integer dec2;


	@NotNull
	private Integer dec3;


	@NotEmpty
	private String hex1;


	@NotEmpty
	private String hex2;


	@NotEmpty
	private String hex3;


	private Boolean active;


	public String getCurrencyName() {
		return currencyName;	
	}
		
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;	
	}

	public List<CountryDetails> getCountryList() {
		return countryList;
	}
	public void setCountryList(
				List<CountryDetails> countryList) {
			this.countryList = countryList;
	}

	public String getCountry() {
		return country;	
	}
		
	public void setCountry(String country) {
		this.country = country;	
	}

	public Long getCountryId() {
		return countryId;	
	}
		
	public void setCountryId(Long countryId) {
		this.countryId = countryId;	
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


	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
		
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCalledFrom() {
		return calledFrom;
	}

	public void setCalledFrom(String calledFrom) {
		this.calledFrom = calledFrom;
	}

	public Boolean getCreated() {
		return created;
	}

	public void setCreated(Boolean created) {
		this.created = created;
	}
	
}