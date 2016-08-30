package co.com.cybersoft.purchase.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.purchase.tables.persistence.domain.ExchangeRate;
import co.com.cybersoft.util.EmbeddedField;
import java.lang.reflect.Method;
import co.com.cybersoft.purchase.tables.core.domain.CurrencyDetails;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ExchangeRateDetails {
	
	private Long localCurrencyId;


	private String localCurrency;


	private Long foreignCurrencyId;


	private String foreignCurrency;


	private Date date;


	private Double exchangeRate;


	private Double variation;


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
	
	public Long getLocalCurrencyId() {
		return localCurrencyId;	
	}
		
	public void setLocalCurrencyId(Long localCurrencyId) {
		this.localCurrencyId = localCurrencyId;	
	}
	public String getLocalCurrency() {
		return localCurrency;	
	}
		
	public void setLocalCurrency(String localCurrency) {
		this.localCurrency = localCurrency;	
	}
	public Long getForeignCurrencyId() {
		return foreignCurrencyId;	
	}
		
	public void setForeignCurrencyId(Long foreignCurrencyId) {
		this.foreignCurrencyId = foreignCurrencyId;	
	}
	public String getForeignCurrency() {
		return foreignCurrency;	
	}
		
	public void setForeignCurrency(String foreignCurrency) {
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

	
	public ExchangeRateDetails toExchangeRateDetails(ExchangeRate entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String _embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method _method = ExchangeRateDetails.class.getMethod("get"+embeddedField.getName());
				String _invoke = (String) _method.invoke(this);
				_embedded+=" - "+_invoke;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.localCurrency=entity.getLocalCurrency().getCode().getCurrency()+" - "+entity.getLocalCurrency().getCurrency()+_embedded;
		this.localCurrencyId=entity.getLocalCurrency().getId();
		this.foreignCurrency=entity.getForeignCurrency().getCode().getCurrency()+" - "+entity.getForeignCurrency().getCurrency()+_embedded;
		this.foreignCurrencyId=entity.getForeignCurrency().getId();

		return this;
	}
}