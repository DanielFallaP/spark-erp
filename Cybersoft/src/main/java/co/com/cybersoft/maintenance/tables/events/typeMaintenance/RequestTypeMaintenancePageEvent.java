package co.com.cybersoft.maintenance.tables.events.typeMaintenance;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.typeMaintenance.TypeMaintenanceFilterInfo;

/**
 * Event class for TypeMaintenance
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestTypeMaintenancePageEvent {

	private Pageable pageable;
	private TypeMaintenanceFilterInfo filter;
	
	public RequestTypeMaintenancePageEvent(Pageable pageable, TypeMaintenanceFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestTypeMaintenancePageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public TypeMaintenanceFilterInfo getFilter() {
		return filter;
	}
}