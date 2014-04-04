package co.com.cybersoft.persistence.services.units;

import org.springframework.data.domain.Page;

import co.com.cybersoft.events.units.RequestUnitsEvent;
import co.com.cybersoft.events.units.UnitsEvent;
import co.com.cybersoft.persistence.domain.MeasurementUnit;
import co.com.cybersoft.persistence.repository.MeasurementUnitRepository;

public class UnitPersistenceServiceImpl implements UnitPersistenceService{

	private final MeasurementUnitRepository unitRepository;
	
	public UnitPersistenceServiceImpl(final MeasurementUnitRepository unitRepository){
		this.unitRepository=unitRepository;
	}
	
	
	@Override
	public UnitsEvent requestUnits(RequestUnitsEvent event) {
		Page<MeasurementUnit> units = unitRepository.findAll(event.getPageable());
		return new UnitsEvent(units);
	}

}
