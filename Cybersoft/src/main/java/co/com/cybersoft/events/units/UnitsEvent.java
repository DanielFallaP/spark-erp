package co.com.cybersoft.events.units;

import org.springframework.data.domain.Page;

import co.com.cybersoft.persistence.domain.MeasurementUnit;

public class UnitsEvent {

	private Page<MeasurementUnit> units;
	
	public UnitsEvent(Page<MeasurementUnit> units){
		this.units=units;
	}

	public Page<MeasurementUnit> getUnits() {
		return units;
	}	
	
}
