package co.com.cybersoft.events.measurementUnits;

import co.com.cybersoft.core.domain.MeasurementUnitsDetails;

/**
 * Event class for MeasurementUnits
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class MeasurementUnitsDetailsEvent {
	
	private MeasurementUnitsDetails measurementUnitsDetails;
	
	public MeasurementUnitsDetailsEvent(MeasurementUnitsDetails measurementUnitsDetails){
		this.measurementUnitsDetails=measurementUnitsDetails;
	}

	public MeasurementUnitsDetails getMeasurementUnitsDetails() {
		return measurementUnitsDetails;
	}

}