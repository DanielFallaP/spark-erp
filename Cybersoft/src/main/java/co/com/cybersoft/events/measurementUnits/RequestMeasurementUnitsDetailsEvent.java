package co.com.cybersoft.events.measurementUnits;

/**
 * Event class for MeasurementUnits
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestMeasurementUnitsDetailsEvent {

	private String id;
	
	private String description;
	
	public RequestMeasurementUnitsDetailsEvent(String id){
		this.id=id;
	}

	public String getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}