package co.com.cybersoft.events.measurementUnit;

/**
 * Event class for MeasurementUnit
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestMeasurementUnitDetailsEvent {

	private String id;
	
	public RequestMeasurementUnitDetailsEvent(String id){
		this.id=id;
	}

	public String getId() {
		return id;
	}
}