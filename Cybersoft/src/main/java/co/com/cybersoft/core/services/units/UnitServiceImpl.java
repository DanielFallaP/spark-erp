package co.com.cybersoft.core.services.units;

import co.com.cybersoft.events.units.RequestUnitsEvent;
import co.com.cybersoft.events.units.UnitsEvent;
import co.com.cybersoft.persistence.services.units.UnitPersistenceService;

public class UnitServiceImpl implements UnitService{

	private final UnitPersistenceService unitPersistenceService;
	
	public UnitServiceImpl(UnitPersistenceService unitPersistenceService) {
		this.unitPersistenceService=unitPersistenceService;
	}

	@Override
	public UnitsEvent requestUnits(RequestUnitsEvent event) {
		return unitPersistenceService.requestUnits(event);
	}

}
