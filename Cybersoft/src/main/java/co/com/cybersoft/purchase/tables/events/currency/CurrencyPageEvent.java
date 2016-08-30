package co.com.cybersoft.purchase.tables.events.currency;

import org.springframework.data.domain.Page;

import co.com.cybersoft.purchase.tables.persistence.domain.Currency;
import co.com.cybersoft.purchase.tables.core.domain.CurrencyDetails;
import co.com.cybersoft.purchase.tables.persistence.domain.Currency;
import java.util.List;

/**
 * Event class for Currency
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class CurrencyPageEvent {
	private Page<Currency> currencyPage;
	
	private List<Currency> allList;
	
	private Long totalCount;
	
	private List<CurrencyDetails> currencyList;



	
	public CurrencyPageEvent(){
    }
	public CurrencyPageEvent(List<CurrencyDetails>  currencyList){
			this.currencyList= currencyList;
	}



	
	public List<CurrencyDetails> getCurrencyList() {
	return currencyList;
	}



	
	public List<Currency> getAllList() {
		return allList;
	}

	public void setAllList(List<Currency> allList) {
		this.allList = allList;
	}
	
	public CurrencyPageEvent(Page<Currency>  currencyPage){
		this.currencyPage= currencyPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public CurrencyPageEvent(Page<Currency>  currencyPage, Long totalCount){
		this.currencyPage= currencyPage;
		this.totalCount=totalCount;
	}

	public Page<Currency> getCurrencyPage() {
		return currencyPage;
	}
	
	
}