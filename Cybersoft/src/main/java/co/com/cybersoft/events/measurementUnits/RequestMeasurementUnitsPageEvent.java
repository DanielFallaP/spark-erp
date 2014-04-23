package co.com.cybersoft.events.measurementUnits;

import org.springframework.data.domain.Pageable;

/**
 * Event class for MeasurementUnits
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestMeasurementUnitsPageEvent {

	private Pageable pageable;
	
	public RequestMeasurementUnitsPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}