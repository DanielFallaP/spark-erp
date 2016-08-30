package co.com.cybersoft.purchase.tables.events.currencyCode;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.purchase.tables.web.domain.currencyCode.CurrencyCodeFilterInfo;

/**
 * Event class for CurrencyCode
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestCurrencyCodePageEvent {

	private Pageable pageable;
	private CurrencyCodeFilterInfo filter;
	
	public RequestCurrencyCodePageEvent(Pageable pageable, CurrencyCodeFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestCurrencyCodePageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public CurrencyCodeFilterInfo getFilter() {
		return filter;
	}
}