package co.com.cybersoft.maintenance.tables.events.measurementUnit;

import co.com.cybersoft.maintenance.tables.core.domain.MeasurementUnitDetails;

/**
 * Event class for MeasurementUnit
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class MeasurementUnitDetailsEvent {
	
	private MeasurementUnitDetails measurementUnitDetails;
	
	public MeasurementUnitDetailsEvent(MeasurementUnitDetails measurementUnitDetails){
		this.measurementUnitDetails=measurementUnitDetails;
	}

	public MeasurementUnitDetails getMeasurementUnitDetails() {
		return measurementUnitDetails;
	}

}