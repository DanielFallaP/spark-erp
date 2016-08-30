package co.com.cybersoft.purchase.tables.events.country;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.purchase.tables.web.domain.country.CountryFilterInfo;

/**
 * Event class for Country
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestCountryPageEvent {

	private Pageable pageable;
	private CountryFilterInfo filter;
	
	public RequestCountryPageEvent(Pageable pageable, CountryFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestCountryPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public CountryFilterInfo getFilter() {
		return filter;
	}
}