package co.com.cybersoft.purchase.tables.web.domain.exchangeRate;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import co.com.cybersoft.util.CyberUtils;

import java.util.Date;
import java.util.ArrayList;
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

	private String fffilterNature;
	
	public String getFffilterNature() {
		return fffilterNature;
	}

	public void setFffilterNature(String fffilterNature) {
		this.fffilterNature = fffilterNature;
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
			ffilterAsText+=(index!=0?(fil.getFffilterNature().equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
			if (fil.getLocalCurrency()!=null && !fil.getLocalCurrency().equals(""))ffilterAsText+=fil.getLocalCurrency()+" ";
			if (fil.getForeignCurrency()!=null && !fil.getForeignCurrency().equals(""))ffilterAsText+=fil.getForeignCurrency()+" ";
			if (fil.getDate()!=null && !fil.getDate().equals(""))ffilterAsText+=fil.getDate()+" ";
			if (fil.getExchangeRate()!=null && !fil.getExchangeRate().equals(""))ffilterAsText+=fil.getExchangeRate()+" ";
			if (fil.getVariation()!=null && !fil.getVariation().equals(""))ffilterAsText+=fil.getVariation()+" ";
			if (fil.getActive()!=null && !fil.getActive().equals(""))ffilterAsText+=fil.getActive()+" ";

			if (fil.getDateOfModification()!=null && !fil.getDateOfModification().equals(""))ffilterAsText+=fil.getDateOfModification()+" ";
			if (fil.getUserName()!=null && !fil.getUserName().equals(""))ffilterAsText+=fil.getUserName()+" ";
			if (fil.getDateOfCreation()!=null && !fil.getDateOfCreation().equals(""))ffilterAsText+=fil.getDateOfCreation()+" ";
			if (fil.getCreatedBy()!=null && !fil.getCreatedBy().equals(""))ffilterAsText+=fil.getCreatedBy()+" ";
			
			index++;
		}
		
		ffilterAsText+=(index!=0?(fffilterNature.equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
		if (localCurrency!=null && !localCurrency.equals(""))ffilterAsText+=localCurrency+" ";if (foreignCurrency!=null && !foreignCurrency.equals(""))ffilterAsText+=foreignCurrency+" ";if (date!=null && !date.equals(""))ffilterAsText+=date+" ";if (exchangeRate!=null && !exchangeRate.equals(""))ffilterAsText+=exchangeRate+" ";if (variation!=null && !variation.equals(""))ffilterAsText+=variation+" ";if (active!=null && !active.equals(""))ffilterAsText+=active+" ";
		if (this.getDateOfModification()!=null && !this.getDateOfModification().equals(""))ffilterAsText+=this.getDateOfModification()+" ";
		if (this.getUserName()!=null && !this.getUserName().equals(""))ffilterAsText+=this.getUserName()+" ";
		if (this.getDateOfCreation()!=null && !this.getDateOfCreation().equals(""))ffilterAsText+=this.getDateOfCreation()+" ";
		if (this.getCreatedBy()!=null && !this.getCreatedBy().equals(""))ffilterAsText+=this.getCreatedBy()+" ";
		
		return ffilterAsText;
	}

	public void setFfilterAsText(String ffilterAsText) {
		this.ffilterAsText = ffilterAsText;
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