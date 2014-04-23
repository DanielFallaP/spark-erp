package co.com.cybersoft.events.measurementUnits;

import co.com.cybersoft.core.domain.MeasurementUnitsDetails;

/**
 * Event class for MeasurementUnits
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateMeasurementUnitsEvent {
		
	private MeasurementUnitsDetails measurementUnitsDetails;
	
	public CreateMeasurementUnitsEvent(MeasurementUnitsDetails measurementUnitsDetails){
		this.measurementUnitsDetails=measurementUnitsDetails;
	}

	public MeasurementUnitsDetails getMeasurementUnitsDetails() {
		return measurementUnitsDetails;
	}
	
	
}