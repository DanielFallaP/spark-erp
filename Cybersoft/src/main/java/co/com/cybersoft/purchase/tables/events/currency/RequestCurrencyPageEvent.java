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
	private Company company;
	
	public RequestCurrencyPageEvent(Pageable pageable, CurrencyFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public Company getCompany() {
		return company;
	}



	public void setCompany(Company company) {
		this.company = company;
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