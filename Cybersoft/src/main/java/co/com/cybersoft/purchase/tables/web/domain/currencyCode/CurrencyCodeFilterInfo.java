package co.com.cybersoft.purchase.tables.web.domain.currencyCode;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import co.com.cybersoft.util.CyberUtils;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CurrencyCodeFilterInfo {
	private String userName;

	private String dateOfCreation;
	
	private String dateOfModification;
	
	private String createdBy;
	
	private String aaaaction;
	
	private Integer selectedFilterPage;
	
	private String selectedFilterField;
	
	private Boolean changeSortingFieldDirection;

	private String currencyName;


	public String getCurrencyName() {
		return currencyName;	
	}
		
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;	
	}

	private String country;


	public String getCountry() {
		return country;	
	}
		
	public void setCountry(String country) {
		this.country = country;	
	}

	private String currency;


	public String getCurrency() {
		return currency;	
	}
		
	public void setCurrency(String currency) {
		this.currency = currency;	
	}

	private String symbol;


	public String getSymbol() {
		return symbol;	
	}
		
	public void setSymbol(String symbol) {
		this.symbol = symbol;	
	}

	private String dec1;


	public String getDec1() {
		return dec1;	
	}
		
	public void setDec1(String dec1) {
		this.dec1 = dec1;	
	}

	private String dec2;


	public String getDec2() {
		return dec2;	
	}
		
	public void setDec2(String dec2) {
		this.dec2 = dec2;	
	}

	private String dec3;


	public String getDec3() {
		return dec3;	
	}
		
	public void setDec3(String dec3) {
		this.dec3 = dec3;	
	}

	private String hex1;


	public String getHex1() {
		return hex1;	
	}
		
	public void setHex1(String hex1) {
		this.hex1 = hex1;	
	}

	private String hex2;


	public String getHex2() {
		return hex2;	
	}
		
	public void setHex2(String hex2) {
		this.hex2 = hex2;	
	}

	private String hex3;


	public String getHex3() {
		return hex3;	
	}
		
	public void setHex3(String hex3) {
		this.hex3 = hex3;	
	}

	private String active;


	public String getActive() {
		return active;	
	}
		
	public void setActive(String active) {
		this.active = active;	
	}


	
	private String fffilterNature;
	
	public String getFffilterNature() {
		return fffilterNature;
	}

	public void setFffilterNature(String fffilterNature) {
		this.fffilterNature = fffilterNature;
	}
		
	private String ffilterAsText="All";
	
	private Boolean aaddFilter=Boolean.FALSE;
	
	private List<CurrencyCodeFilterInfo> currencyCodeFilterList=new ArrayList<CurrencyCodeFilterInfo>();
	
	public List<CurrencyCodeFilterInfo> getCurrencyCodeFilterList() {
		return currencyCodeFilterList;
	}

	public void setCurrencyCodeFilterList(
			List<CurrencyCodeFilterInfo> currencyCodeFilterList) {
		this.currencyCodeFilterList = currencyCodeFilterList;
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
		for (CurrencyCodeFilterInfo fil : currencyCodeFilterList) {
			ffilterAsText+=(index!=0?(fil.getFffilterNature().equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
			if (fil.getCurrencyName()!=null && !fil.getCurrencyName().equals(""))ffilterAsText+=fil.getCurrencyName()+" ";
			if (fil.getCountry()!=null && !fil.getCountry().equals(""))ffilterAsText+=fil.getCountry()+" ";
			if (fil.getCurrency()!=null && !fil.getCurrency().equals(""))ffilterAsText+=fil.getCurrency()+" ";
			if (fil.getSymbol()!=null && !fil.getSymbol().equals(""))ffilterAsText+=fil.getSymbol()+" ";
			if (fil.getDec1()!=null && !fil.getDec1().equals(""))ffilterAsText+=fil.getDec1()+" ";
			if (fil.getDec2()!=null && !fil.getDec2().equals(""))ffilterAsText+=fil.getDec2()+" ";
			if (fil.getDec3()!=null && !fil.getDec3().equals(""))ffilterAsText+=fil.getDec3()+" ";
			if (fil.getHex1()!=null && !fil.getHex1().equals(""))ffilterAsText+=fil.getHex1()+" ";
			if (fil.getHex2()!=null && !fil.getHex2().equals(""))ffilterAsText+=fil.getHex2()+" ";
			if (fil.getHex3()!=null && !fil.getHex3().equals(""))ffilterAsText+=fil.getHex3()+" ";
			if (fil.getActive()!=null && !fil.getActive().equals(""))ffilterAsText+=fil.getActive()+" ";

			if (fil.getDateOfModification()!=null && !fil.getDateOfModification().equals(""))ffilterAsText+=fil.getDateOfModification()+" ";
			if (fil.getUserName()!=null && !fil.getUserName().equals(""))ffilterAsText+=fil.getUserName()+" ";
			if (fil.getDateOfCreation()!=null && !fil.getDateOfCreation().equals(""))ffilterAsText+=fil.getDateOfCreation()+" ";
			if (fil.getCreatedBy()!=null && !fil.getCreatedBy().equals(""))ffilterAsText+=fil.getCreatedBy()+" ";
			
			index++;
		}
		
		ffilterAsText+=(index!=0?(fffilterNature.equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
		if (currencyName!=null && !currencyName.equals(""))ffilterAsText+=currencyName+" ";if (country!=null && !country.equals(""))ffilterAsText+=country+" ";if (currency!=null && !currency.equals(""))ffilterAsText+=currency+" ";if (symbol!=null && !symbol.equals(""))ffilterAsText+=symbol+" ";if (dec1!=null && !dec1.equals(""))ffilterAsText+=dec1+" ";if (dec2!=null && !dec2.equals(""))ffilterAsText+=dec2+" ";if (dec3!=null && !dec3.equals(""))ffilterAsText+=dec3+" ";if (hex1!=null && !hex1.equals(""))ffilterAsText+=hex1+" ";if (hex2!=null && !hex2.equals(""))ffilterAsText+=hex2+" ";if (hex3!=null && !hex3.equals(""))ffilterAsText+=hex3+" ";if (active!=null && !active.equals(""))ffilterAsText+=active+" ";
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