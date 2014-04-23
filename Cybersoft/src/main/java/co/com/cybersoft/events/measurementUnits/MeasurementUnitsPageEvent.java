package co.com.cybersoft.events.measurementUnits;

import org.springframework.data.domain.Page;

import co.com.cybersoft.persistence.domain.MeasurementUnits;
import co.com.cybersoft.core.domain.MeasurementUnitsDetails;
import co.com.cybersoft.persistence.domain.MeasurementUnits;
import java.util.List;

/**
 * Event class for MeasurementUnits
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class MeasurementUnitsPageEvent {
	private Page<MeasurementUnits> measurementUnitsPage;
	
	private List<MeasurementUnitsDetails> measurementUnitsList;



	
	public MeasurementUnitsPageEvent(List<MeasurementUnitsDetails>  measurementUnitsList){
			this.measurementUnitsList= measurementUnitsList;
	}



	
	public List<MeasurementUnitsDetails> getMeasurementUnitsList() {
	return measurementUnitsList;
	}



	
	public MeasurementUnitsPageEvent(Page<MeasurementUnits>  measurementUnitsPage){
		this.measurementUnitsPage= measurementUnitsPage;
	}

	public Page<MeasurementUnits> getMeasurementUnitsPage() {
		return measurementUnitsPage;
	}
	
}