package co.com.cybersoft.purchase.tables.events.currency;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.purchase.tables.web.domain.currency.CurrencyFilterInfo;

/**
 * Event class for Currency
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestCurrencyPageEvent {

	private Pageable pageable;
	private CurrencyFilterInfo filter;
	
	public RequestCurrencyPageEvent(Pageable pageable, CurrencyFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestCurrencyPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public CurrencyFilterInfo getFilter() {
		return filter;
	}
}