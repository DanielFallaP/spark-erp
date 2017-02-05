package co.com.cybersoft.maintenance.tables.events.measurementUnit;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.MeasurementUnit;
import co.com.cybersoft.maintenance.tables.core.domain.MeasurementUnitDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.MeasurementUnit;
import java.util.List;

/**
 * Event class for MeasurementUnit
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class MeasurementUnitPageEvent {
	private Page<MeasurementUnit> measurementUnitPage;
	
	private List<MeasurementUnit> allList;
	
	private Long totalCount;
	
	private List<MeasurementUnitDetails> measurementUnitList;



	
	public MeasurementUnitPageEvent(){
    }
	public MeasurementUnitPageEvent(List<MeasurementUnitDetails>  measurementUnitList){
			this.measurementUnitList= measurementUnitList;
	}



	
	public List<MeasurementUnitDetails> getMeasurementUnitList() {
	return measurementUnitList;
	}



	
	public List<MeasurementUnit> getAllList() {
		return allList;
	}

	public void setAllList(List<MeasurementUnit> allList) {
		this.allList = allList;
	}
	
	public MeasurementUnitPageEvent(Page<MeasurementUnit>  measurementUnitPage){
		this.measurementUnitPage= measurementUnitPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public MeasurementUnitPageEvent(Page<MeasurementUnit>  measurementUnitPage, Long totalCount){
		this.measurementUnitPage= measurementUnitPage;
		this.totalCount=totalCount;
	}

	public Page<MeasurementUnit> getMeasurementUnitPage() {
		return measurementUnitPage;
	}
	
	
}