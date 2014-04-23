package co.com.cybersoft.events.measurementUnits;

import co.com.cybersoft.core.domain.MeasurementUnitsDetails;

/**
 * Event class for MeasurementUnits
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class MeasurementUnitsModificationEvent {

	private MeasurementUnitsDetails measurementUnitsDetails;
	
	public MeasurementUnitsModificationEvent(MeasurementUnitsDetails measurementUnitsDetails){
		this.measurementUnitsDetails=measurementUnitsDetails;
	}

	public MeasurementUnitsDetails getMeasurementUnitsDetails() {
		return measurementUnitsDetails;
	}
	
}