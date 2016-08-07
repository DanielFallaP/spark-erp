package co.com.cybersoft.purchase.tables.core.domain;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import co.com.cybersoft.purchase.tables.persistence.domain.Currency;
import co.com.cybersoft.purchase.tables.core.domain.CurrencyCodeDetails;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CurrencyDetails {
	
	private Long codeId;


	private String code;


	private String currency;


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
	
	public Long getCodeId() {
		return codeId;	
	}
		
	public void setCodeId(Long codeId) {
		this.codeId = codeId;	
	}
	public String getCode() {
		return code;	
	}
		
	public void setCode(String code) {
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

	
	public CurrencyDetails toCurrencyDetails(Currency entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method method = CurrencyDetails.class.getMethod("get"+embeddedField.getName());
				String invoke = (String) method.invoke(this);
				embedded+="-"+invoke;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.code=entity.getCode().getCurrency()+embedded;
		this.codeId=entity.getCode().getId();

		return this;
	}
}