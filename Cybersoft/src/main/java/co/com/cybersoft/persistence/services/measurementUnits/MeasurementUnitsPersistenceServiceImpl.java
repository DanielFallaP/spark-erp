package co.com.cybersoft.persistence.services.measurementUnits;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.MeasurementUnitsDetails;
import co.com.cybersoft.events.measurementUnits.CreateMeasurementUnitsEvent;
import co.com.cybersoft.events.measurementUnits.MeasurementUnitsDetailsEvent;
import co.com.cybersoft.events.measurementUnits.MeasurementUnitsPageEvent;
import co.com.cybersoft.events.measurementUnits.MeasurementUnitsModificationEvent;
import co.com.cybersoft.events.measurementUnits.RequestMeasurementUnitsDetailsEvent;
import co.com.cybersoft.events.measurementUnits.RequestMeasurementUnitsPageEvent;
import co.com.cybersoft.persistence.domain.MeasurementUnits;
import co.com.cybersoft.persistence.repository.MeasurementUnitsRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class MeasurementUnitsPersistenceServiceImpl implements MeasurementUnitsPersistenceService{

	private final MeasurementUnitsRepository measurementUnitsRepository;
	
	public MeasurementUnitsPersistenceServiceImpl(final MeasurementUnitsRepository measurementUnitsRepository) {
		this.measurementUnitsRepository=measurementUnitsRepository;
	}
	
	@Override
	public MeasurementUnitsDetailsEvent createMeasurementUnits(CreateMeasurementUnitsEvent event) throws Exception{
		MeasurementUnits measurementUnits = MeasurementUnits.fromMeasurementUnitsDetails(event.getMeasurementUnitsDetails());
		measurementUnits = measurementUnitsRepository.save(measurementUnits);
		return new MeasurementUnitsDetailsEvent(measurementUnits.toMeasurementUnitsDetails());
	}

	@Override
	public MeasurementUnitsPageEvent requestMeasurementUnitsPage(RequestMeasurementUnitsPageEvent event) throws Exception {
		Page<MeasurementUnits> measurementUnitss = measurementUnitsRepository.findAll(event.getPageable());
		return new MeasurementUnitsPageEvent(measurementUnitss);
	}

	@Override
	public MeasurementUnitsDetailsEvent requestMeasurementUnitsDetails(RequestMeasurementUnitsDetailsEvent event) throws Exception {
		MeasurementUnits measurementUnits = measurementUnitsRepository.findByCode(event.getId());
		MeasurementUnitsDetails measurementUnitsDetails = measurementUnits.toMeasurementUnitsDetails();
		return new MeasurementUnitsDetailsEvent(measurementUnitsDetails);
	}

	@Override
	public MeasurementUnitsDetailsEvent modifyMeasurementUnits(MeasurementUnitsModificationEvent event) throws Exception {
		MeasurementUnits measurementUnits = MeasurementUnits.fromMeasurementUnitsDetails(event.getMeasurementUnitsDetails());
		measurementUnits = measurementUnitsRepository.save(measurementUnits);
		return new MeasurementUnitsDetailsEvent(measurementUnits.toMeasurementUnitsDetails());
	}
	
	@Override
	public MeasurementUnitsPageEvent requestAll() throws Exception {
		List<MeasurementUnits> all = measurementUnitsRepository.findAll();
		List<MeasurementUnitsDetails> list = new ArrayList<MeasurementUnitsDetails>();
		for (MeasurementUnits measurementUnits : all) {
			list.add(measurementUnits.toMeasurementUnitsDetails());
		}
		return new MeasurementUnitsPageEvent(list);
	}

}