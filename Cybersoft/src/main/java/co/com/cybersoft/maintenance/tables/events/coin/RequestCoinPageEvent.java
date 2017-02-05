package co.com.cybersoft.maintenance.tables.events.coin;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.coin.CoinFilterInfo;

/**
 * Event class for Coin
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestCoinPageEvent {

	private Pageable pageable;
	private CoinFilterInfo filter;
	
	public RequestCoinPageEvent(Pageable pageable, CoinFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestCoinPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public CoinFilterInfo getFilter() {
		return filter;
	}
}