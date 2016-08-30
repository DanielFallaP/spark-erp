package co.com.cybersoft.purchase.tables.web.domain.currency;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import co.com.cybersoft.purchase.tables.core.domain.CurrencyCodeDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for currency
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CurrencyInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private Long id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	private Long codeId;


	private String code;


	private List<CurrencyCodeDetails> codeList;
	@Length(max=40)
	@NotEmpty
	private String currency;


	private Boolean active;


	public List<CurrencyCodeDetails> getCodeList() {
		return codeList;
	}
	public void setCodeList(
				List<CurrencyCodeDetails> codeList) {
			this.codeList = codeList;
	}

	public String getCode() {
		return code;	
	}
		
	public void setCode(String code) {
		this.code = code;	
	}

	public Long getCodeId() {
		return codeId;	
	}
		
	public void setCodeId(Long codeId) {
		this.codeId = codeId;	
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