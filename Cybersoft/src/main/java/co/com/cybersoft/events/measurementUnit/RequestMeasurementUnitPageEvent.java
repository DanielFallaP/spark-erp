package co.com.cybersoft.events.measurementUnit;

import org.springframework.data.domain.Pageable;

/**
 * Event class for MeasurementUnit
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestMeasurementUnitPageEvent {

	private Pageable pageable;
	
	public RequestMeasurementUnitPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}