package co.com.cybersoft.purchase.tables.web.domain.exchangeRate;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ExchangeRateFilterInfo {
	private String userName;

	private String dateOfCreation;
	
	private String dateOfModification;
	
	private String createdBy;
	
	private String aaaaction;
	
	private Integer selectedFilterPage;
	
	private String selectedFilterField;
	
	private Boolean changeSortingFieldDirection;

	private String localCurrency;
	
	private String ffilterAsText="All";
	
	private Boolean aaddFilter=Boolean.FALSE;
	
	private List<ExchangeRateFilterInfo> exchangeRateFilterList=new ArrayList<ExchangeRateFilterInfo>();
	
	public List<ExchangeRateFilterInfo> getExchangeRateFilterList() {
		return exchangeRateFilterList;
	}

	public void setExchangeRateFilterList(
			List<ExchangeRateFilterInfo> exchangeRateFilterList) {
		this.exchangeRateFilterList = exchangeRateFilterList;
	}

	public Boolean getAaddFilter() {
		return aaddFilter;
	}

	public void setAaddFilter(Boolean aaddFilter) {
		this.aaddFilter = aaddFilter;
	}

	public String getFfilterAsText() {
		ffilterAsText="";
		int index=0;
		for (ExchangeRateFilterInfo fil : exchangeRateFilterList) {
			if (fil.getDate()!=null && !fil.getDate().equals(""))
				ffilterAsText+=(index!=0?" UNION ":"")+fil.getDate();
			index++;
		}
		if (date!=null && !date.equals(""))
			ffilterAsText+=(index!=0?" UNION ":"")+date;
		return ffilterAsText;
	}

	public void setFfilterAsText(String ffilterAsText) {
		this.ffilterAsText = ffilterAsText;
	}

	public String getLocalCurrency() {
		return localCurrency;	
	}
		
	public void setLocalCurrency(String localCurrency) {
		this.localCurrency = localCurrency;	
	}

	private String foreignCurrency;


	public String getForeignCurrency() {
		return foreignCurrency;	
	}
		
	public void setForeignCurrency(String foreignCurrency) {
		this.foreignCurrency = foreignCurrency;	
	}

	private String date;


	public String getDate() {
		return date;	
	}
		
	public void setDate(String date) {
		this.date = date;	
	}

	private String exchangeRate;


	public String getExchangeRate() {
		return exchangeRate;	
	}
		
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;	
	}

	private String variation;


	public String getVariation() {
		return variation;	
	}
		
	public void setVariation(String variation) {
		this.variation = variation;	
	}

	private String active;


	public String getActive() {
		return active;	
	}
		
	public void setActive(String active) {
		this.active = active;	
	}


	
	public String getAaaaction() {
		return aaaaction;
	}

	public void setAaaaction(String aaaaction) {
		this.aaaaction = aaaaction;
	}
	
	public Boolean getChangeSortingFieldDirection() {
		return changeSortingFieldDirection;
	}

	public void setChangeSortingFieldDirection(Boolean changeSortingFieldDirection) {
		this.changeSortingFieldDirection = changeSortingFieldDirection;
	}
	
	public Integer getSelectedFilterPage() {
		return selectedFilterPage;
	}

	public void setSelectedFilterPage(Integer selectedFilterPage) {
		this.selectedFilterPage = selectedFilterPage;
	}

	public String getSelectedFilterField() {
		return selectedFilterField;
	}

	public void setSelectedFilterField(String selectedFilterField) {
		this.selectedFilterField = selectedFilterField;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getDateOfCreation() {
		return dateOfCreation;
	}
	
	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	
	public String getDateOfModification() {
		return dateOfModification;
	}
	
	public void setDateOfModification(String dateOfModification) {
		this.dateOfModification = dateOfModification;
	}
		
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}