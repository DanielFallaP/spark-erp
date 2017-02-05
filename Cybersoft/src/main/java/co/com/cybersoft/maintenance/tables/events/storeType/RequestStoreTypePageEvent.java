package co.com.cybersoft.maintenance.tables.events.storeType;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.storeType.StoreTypeFilterInfo;

/**
 * Event class for StoreType
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestStoreTypePageEvent {

	private Pageable pageable;
	private StoreTypeFilterInfo filter;
	
	public RequestStoreTypePageEvent(Pageable pageable, StoreTypeFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestStoreTypePageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public StoreTypeFilterInfo getFilter() {
		return filter;
	}
}