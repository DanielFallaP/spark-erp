package co.com.cybersoft.purchase.tables.events.exchangeRate;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.purchase.tables.web.domain.exchangeRate.ExchangeRateFilterInfo;

/**
 * Event class for ExchangeRate
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestExchangeRatePageEvent {

	private Pageable pageable;
	private ExchangeRateFilterInfo filter;
	
	public RequestExchangeRatePageEvent(Pageable pageable, ExchangeRateFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestExchangeRatePageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public ExchangeRateFilterInfo getFilter() {
		return filter;
	}
}