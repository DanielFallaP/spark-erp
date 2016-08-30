package co.com.cybersoft.purchase.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.purchase.tables.persistence.domain.CustomerTenancy;
import co.com.cybersoft.util.EmbeddedField;
import java.lang.reflect.Method;
import co.com.cybersoft.purchase.tables.core.domain.CurrencyDetails;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CustomerTenancyDetails {
	
	private String softwareTradeMark;


	private String softwareVersion;


	private String softwareSerialNo;


	private Boolean doubleCurrency;


	private Long localCurrencyId;


	private String localCurrency;


	private Long foreignCurrencyId;


	private String foreignCurrency;


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
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public CustomerTenancyDetails toCustomerTenancyDetails(CustomerTenancy entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String _embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method _method = CustomerTenancyDetails.class.getMethod("get"+embeddedField.getName());
				String _invoke = (String) _method.invoke(this);
				_embedded+=" - "+_invoke;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.softwareTradeMark=softwareTradeMark+_embedded;
		this.softwareVersion=softwareVersion+_embedded;
		this.softwareSerialNo=softwareSerialNo+_embedded;
		this.localCurrency=entity.getLocalCurrency().getCode().getCurrency()+_embedded;
		this.localCurrencyId=entity.getLocalCurrency().getId();
		this.foreignCurrency=entity.getForeignCurrency().getCode().getCurrency()+_embedded;
		this.foreignCurrencyId=entity.getForeignCurrency().getId();

		return this;
	}
}