package co.com.cybersoft.persistence.services.units;

import co.com.cybersoft.events.units.RequestUnitsEvent;
import co.com.cybersoft.events.units.UnitsEvent;

public interface UnitPersistenceService {
	UnitsEvent requestUnits(RequestUnitsEvent event);
}
