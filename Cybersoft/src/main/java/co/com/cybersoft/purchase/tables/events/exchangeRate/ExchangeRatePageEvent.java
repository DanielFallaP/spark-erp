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

	public Page<ExchangeRate> getExchangeRatePage() {
		return exchangeRatePage;
	}
	
	
}