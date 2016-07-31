package co.com.cybersoft.purchase.tables.events.exchangeRate;

import org.springframework.data.domain.Page;

import co.com.cybersoft.purchase.tables.persistence.domain.ExchangeRate;
import co.com.cybersoft.purchase.tables.core.domain.ExchangeRateDetails;
import co.com.cybersoft.purchase.tables.persistence.domain.ExchangeRate;
import java.util.List;

/**
 * Event class for ExchangeRate
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class ExchangeRatePageEvent {
	private Page<ExchangeRate> exchangeRatePage;
	
	private List<ExchangeRate> allList;
	
	private Long totalCount;
	
	private List<ExchangeRateDetails> exchangeRateList;



	
	public ExchangeRatePageEvent(){
    }
	public ExchangeRatePageEvent(List<ExchangeRateDetails>  exchangeRateList){
			this.exchangeRateList= exchangeRateList;
	}



	
	public List<ExchangeRateDetails> getExchangeRateList() {
	return exchangeRateList;
	}



	
	public List<ExchangeRate> getAllList() {
		return allList;
	}

	public void setAllList(List<ExchangeRate> allList) {
		this.allList = allList;
	}
	
	public ExchangeRatePageEvent(Page<ExchangeRate>  exchangeRatePage){
		this.exchangeRatePage= exchangeRatePage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public ExchangeRatePageEvent(Page<ExchangeRate>  exchangeRatePage, Long totalCount){
		this.exchangeRatePage= exchangeRatePage;
		this.totalCount=totalCount;
	}

	public Page<ExchangeRate> getExchangeRatePage() {
		return exchangeRatePage;
	}
	
	
}