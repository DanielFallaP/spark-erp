package co.com.cybersoft.events.measurementUnit;

import java.util.List;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.MeasurementUnitDetails;
import co.com.cybersoft.persistence.domain.MeasurementUnit;

/**
 * Event class for MeasurementUnit
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class MeasurementUnitPageEvent {
	private Page<MeasurementUnit> measurementUnitPage;
	
	private List<MeasurementUnitDetails> measurementUnitList;
	
	public MeasurementUnitPageEvent(Page<MeasurementUnit>  measurementUnitPage){
		this.measurementUnitPage= measurementUnitPage;
	}
	
	public MeasurementUnitPageEvent(List<MeasurementUnitDetails>  measurementUnitList){
		this.measurementUnitList= measurementUnitList;
	}

	public Page<MeasurementUnit> getMeasurementUnitPage() {
		return measurementUnitPage;
	}

	public List<MeasurementUnitDetails> getMeasurementUnitList() {
		return measurementUnitList;
	}

	
}