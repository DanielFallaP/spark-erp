package co.com.cybersoft.maintenance.tables.events.physicalLocation;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.physicalLocation.PhysicalLocationFilterInfo;

/**
 * Event class for PhysicalLocation
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestPhysicalLocationPageEvent {

	private Pageable pageable;
	private PhysicalLocationFilterInfo filter;
	
	public RequestPhysicalLocationPageEvent(Pageable pageable, PhysicalLocationFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestPhysicalLocationPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public PhysicalLocationFilterInfo getFilter() {
		return filter;
	}
}