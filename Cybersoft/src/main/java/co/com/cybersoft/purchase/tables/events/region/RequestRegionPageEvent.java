package co.com.cybersoft.purchase.tables.events.region;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.purchase.tables.web.domain.region.RegionFilterInfo;

/**
 * Event class for Region
 * @author Cybersystems 2015. All rights reserved.
 *
 */
public class RequestRegionPageEvent {

	private Pageable pageable;
	private RegionFilterInfo filter;
	
	public RequestRegionPageEvent(Pageable pageable, RegionFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestRegionPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public RegionFilterInfo getFilter() {
		return filter;
	}
}