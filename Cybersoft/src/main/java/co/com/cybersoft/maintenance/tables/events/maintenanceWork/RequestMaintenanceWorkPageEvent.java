package co.com.cybersoft.maintenance.tables.events.maintenanceWork;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.maintenanceWork.MaintenanceWorkFilterInfo;

/**
 * Event class for MaintenanceWork
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestMaintenanceWorkPageEvent {

	private Pageable pageable;
	private MaintenanceWorkFilterInfo filter;
	
	public RequestMaintenanceWorkPageEvent(Pageable pageable, MaintenanceWorkFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestMaintenanceWorkPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public MaintenanceWorkFilterInfo getFilter() {
		return filter;
	}
}