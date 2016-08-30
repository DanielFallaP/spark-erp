package co.com.cybersoft.purchase.tables.web.domain.customerTenancy;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import co.com.cybersoft.purchase.tables.core.domain.CurrencyDetails;
import co.com.cybersoft.purchase.tables.core.domain.CurrencyDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for customerTenancy
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CustomerTenancyInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private Long id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	private String softwareTradeMark;


	private String softwareVersion;


	private String softwareSerialNo;


	private Boolean doubleCurrency;


	private Long localCurrencyId;


	private String localCurrency;


	private List<CurrencyDetails> localCurrencyList;
	private Long foreignCurrencyId;


	private String foreignCurrency;


	private List<CurrencyDetails> foreignCurrencyList;
	private Boolean active;


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