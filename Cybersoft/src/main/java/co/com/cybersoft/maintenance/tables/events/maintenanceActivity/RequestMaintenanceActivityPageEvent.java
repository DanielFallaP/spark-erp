package co.com.cybersoft.maintenance.tables.events.maintenanceActivity;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.maintenanceActivity.MaintenanceActivityFilterInfo;

/**
 * Event class for MaintenanceActivity
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestMaintenanceActivityPageEvent {

	private Pageable pageable;
	private MaintenanceActivityFilterInfo filter;
	
	public RequestMaintenanceActivityPageEvent(Pageable pageable, MaintenanceActivityFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestMaintenanceActivityPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public MaintenanceActivityFilterInfo getFilter() {
		return filter;
	}
}