package co.com.cybersoft.maintenance.tables.events.measurementUnit;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.measurementUnit.MeasurementUnitFilterInfo;

/**
 * Event class for MeasurementUnit
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestMeasurementUnitPageEvent {

	private Pageable pageable;
	private MeasurementUnitFilterInfo filter;
	
	public RequestMeasurementUnitPageEvent(Pageable pageable, MeasurementUnitFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestMeasurementUnitPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public MeasurementUnitFilterInfo getFilter() {
		return filter;
	}
}