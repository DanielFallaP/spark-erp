package co.com.cybersoft.purchase.tables.web.domain.exchangeRate;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import co.com.cybersoft.purchase.tables.core.domain.CurrencyDetails;
import co.com.cybersoft.purchase.tables.core.domain.CurrencyDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for exchangeRate
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ExchangeRateInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private Long id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	private Long localCurrencyId;


	private String localCurrency;


	private List<CurrencyDetails> localCurrencyList;
	private Long foreignCurrencyId;


	private String foreignCurrency;


	private List<CurrencyDetails> foreignCurrencyList;
	private Date date;


	@NotNull
	private Double exchangeRate;


	@NotNull
	private Double variation;


	private Boolean active;


	public List<CurrencyDetails> getLocalCurrencyList() {
		return localCurrencyList;
	}
	public void setLocalCurrencyList(
				List<CurrencyDetails> localCurrencyList) {
			this.localCurrencyList = localCurrencyList;
	}

	public String getLocalCurrency() {
		return localCurrency;	
	}
		
	public void setLocalCurrency(String localCurrency) {
		this.localCurrency = localCurrency;	
	}

	public Long getLocalCurrencyId() {
		return localCurrencyId;	
	}
		
	public void setLocalCurrencyId(Long localCurrencyId) {
		this.localCurrencyId = localCurrencyId;	
	}

	public List<CurrencyDetails> getForeignCurrencyList() {
		return foreignCurrencyList;
	}
	public void setForeignCurrencyList(
				List<CurrencyDetails> foreignCurrencyList) {
			this.foreignCurrencyList = foreignCurrencyList;
	}

	public String getForeignCurrency() {
		return foreignCurrency;	
	}
		
	public void setForeignCurrency(String foreignCurrency) {
		this.foreignCurrency = foreignCurrency;	
	}

	public Long getForeignCurrencyId() {
		return foreignCurrencyId;	
	}
		
	public void setForeignCurrencyId(Long foreignCurrencyId) {
		this.foreignCurrencyId = foreignCurrencyId;	
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