package co.com.cybersoft.persistence.services.measurementUnit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.MeasurementUnitDetails;
import co.com.cybersoft.events.measurementUnit.CreateMeasurementUnitEvent;
import co.com.cybersoft.events.measurementUnit.MeasurementUnitDetailsEvent;
import co.com.cybersoft.events.measurementUnit.MeasurementUnitPageEvent;
import co.com.cybersoft.events.measurementUnit.MeasurementUnitModificationEvent;
import co.com.cybersoft.events.measurementUnit.RequestMeasurementUnitDetailsEvent;
import co.com.cybersoft.events.measurementUnit.RequestMeasurementUnitPageEvent;
import co.com.cybersoft.persistence.domain.MeasurementUnit;
import co.com.cybersoft.persistence.repository.MeasurementUnitRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class MeasurementUnitPersistenceServiceImpl implements MeasurementUnitPersistenceService{

	private final MeasurementUnitRepository measurementUnitRepository;
	
	public MeasurementUnitPersistenceServiceImpl(final MeasurementUnitRepository measurementUnitRepository) {
		this.measurementUnitRepository=measurementUnitRepository;
	}
	
	@Override
	public MeasurementUnitDetailsEvent createMeasurementUnit(CreateMeasurementUnitEvent event) throws Exception{
		MeasurementUnit measurementUnit = MeasurementUnit.fromMeasurementUnitDetails(event.getMeasurementUnitDetails());
		measurementUnit = measurementUnitRepository.save(measurementUnit);
		return new MeasurementUnitDetailsEvent(measurementUnit.toMeasurementUnitDetails());
	}

	@Override
	public MeasurementUnitPageEvent requestMeasurementUnitPage(RequestMeasurementUnitPageEvent event) throws Exception {
		Page<MeasurementUnit> measurementUnits = measurementUnitRepository.findAll(event.getPageable());
		return new MeasurementUnitPageEvent(measurementUnits);
	}

	@Override
	public MeasurementUnitDetailsEvent requestMeasurementUnitDetails(RequestMeasurementUnitDetailsEvent event) throws Exception {
		MeasurementUnit measurementUnit = measurementUnitRepository.findByCode(event.getId());
		MeasurementUnitDetails measurementUnitDetails = measurementUnit.toMeasurementUnitDetails();
		return new MeasurementUnitDetailsEvent(measurementUnitDetails);
	}

	@Override
	public MeasurementUnitDetailsEvent modifyMeasurementUnit(MeasurementUnitModificationEvent event) throws Exception {
		MeasurementUnit measurementUnit = MeasurementUnit.fromMeasurementUnitDetails(event.getMeasurementUnitDetails());
		measurementUnit = measurementUnitRepository.save(measurementUnit);
		return new MeasurementUnitDetailsEvent(measurementUnit.toMeasurementUnitDetails());
	}

	@Override
	public MeasurementUnitPageEvent requestAll() throws Exception {
		List<MeasurementUnit> all = measurementUnitRepository.findAll();
		List<MeasurementUnitDetails> list = new ArrayList<MeasurementUnitDetails>();
		for (MeasurementUnit measurementUnit : all) {
			list.add(measurementUnit.toMeasurementUnitDetails());
		}
		return new MeasurementUnitPageEvent(list);
	}

}