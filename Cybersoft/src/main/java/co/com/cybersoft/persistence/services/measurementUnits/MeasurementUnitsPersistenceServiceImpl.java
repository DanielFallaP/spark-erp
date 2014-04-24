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
import co.com.cybersoft.persistence.repository.measurementUnits.MeasurementUnitsRepository;
import co.com.cybersoft.persistence.repository.measurementUnits.MeasurementUnitsCustomRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class MeasurementUnitsPersistenceServiceImpl implements MeasurementUnitsPersistenceService{

	private final MeasurementUnitsRepository measurementUnitsRepository;
	
	private final MeasurementUnitsCustomRepo measurementUnitsCustomRepo;
	
	public MeasurementUnitsPersistenceServiceImpl(final MeasurementUnitsRepository measurementUnitsRepository, final MeasurementUnitsCustomRepo measurementUnitsCustomRepo) {
		this.measurementUnitsRepository=measurementUnitsRepository;
		this.measurementUnitsCustomRepo=measurementUnitsCustomRepo;
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
		MeasurementUnitsDetails measurementUnitsDetails=null;
		if (event.getId()!=null){
			MeasurementUnits measurementUnits = measurementUnitsRepository.findByCode(event.getId());
			if (measurementUnits!=null)
				measurementUnitsDetails = measurementUnits.toMeasurementUnitsDetails();
		}
		else{
					MeasurementUnits measurementUnits = measurementUnitsRepository.findByDescription(event.getDescription());
					if (measurementUnits!=null)
						measurementUnitsDetails = measurementUnits.toMeasurementUnitsDetails();
				}
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
	
	@Override
	public MeasurementUnitsPageEvent requestByCodePrefix(String codePrefix) throws Exception {
		List<MeasurementUnits> codes = measurementUnitsCustomRepo.findByStartingCodeNumber(codePrefix);
		ArrayList<MeasurementUnitsDetails> list = new ArrayList<MeasurementUnitsDetails>();
		for (MeasurementUnits measurementUnits : codes) {
			list.add(measurementUnits.toMeasurementUnitsDetails());
		}
		return new MeasurementUnitsPageEvent(list);
	}

	@Override
	public MeasurementUnitsPageEvent requestByContainingDescription(String description) throws Exception {
		ArrayList<MeasurementUnitsDetails> list = new ArrayList<MeasurementUnitsDetails>();
		List<MeasurementUnits> descriptions = measurementUnitsCustomRepo.findByContainingDescription(description);
		for (MeasurementUnits measurementUnits : descriptions) {
			list.add(measurementUnits.toMeasurementUnitsDetails());
		}
		return new MeasurementUnitsPageEvent(list);
	}

}