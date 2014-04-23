package co.com.cybersoft.events.measurementUnits;

/**
 * Event class for MeasurementUnits
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestMeasurementUnitsDetailsEvent {

	private String id;
	
	public RequestMeasurementUnitsDetailsEvent(String id){
		this.id=id;
	}

	public String getId() {
		return id;
	}
}