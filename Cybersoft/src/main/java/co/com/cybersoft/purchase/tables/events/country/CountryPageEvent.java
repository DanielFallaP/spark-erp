package co.com.cybersoft.purchase.tables.events.country;

import org.springframework.data.domain.Page;

import co.com.cybersoft.purchase.tables.persistence.domain.Country;
import co.com.cybersoft.purchase.tables.core.domain.CountryDetails;
import co.com.cybersoft.purchase.tables.persistence.domain.Country;
import java.util.List;

/**
 * Event class for Country
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class CountryPageEvent {
	private Page<Country> countryPage;
	
	private List<Country> allList;
	
	private Long totalCount;
	
	private List<CountryDetails> countryList;



	
	public CountryPageEvent(){
    }
	public CountryPageEvent(List<CountryDetails>  countryList){
			this.countryList= countryList;
	}



	
	public List<CountryDetails> getCountryList() {
	return countryList;
	}



	
	public List<Country> getAllList() {
		return allList;
	}

	public void setAllList(List<Country> allList) {
		this.allList = allList;
	}
	
	public CountryPageEvent(Page<Country>  countryPage){
		this.countryPage= countryPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public CountryPageEvent(Page<Country>  countryPage, Long totalCount){
		this.countryPage= countryPage;
		this.totalCount=totalCount;
	}

	public Page<Country> getCountryPage() {
		return countryPage;
	}
	
	
}