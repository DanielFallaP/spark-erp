package co.com.cybersoft.purchase.tables.events.currency;

import org.springframework.data.domain.Pageable;

import co.com.cybersoft.maintenance.tables.persistence.domain.Company;
import co.com.cybersoft.purchase.tables.web.domain.currency.CurrencyFilterInfo;

/**
 * Event class for Currency
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestCurrencyPageEvent {

	private Pageable pageable;
	private CurrencyFilterInfo filter;
	private Long companyId;
	
	public RequestCurrencyPageEvent(Pageable pageable, CurrencyFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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