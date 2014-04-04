package co.com.cybersoft.core.services.units;

import co.com.cybersoft.events.units.RequestUnitsEvent;
import co.com.cybersoft.events.units.UnitsEvent;

public interface UnitService {
	UnitsEvent requestUnits(RequestUnitsEvent event);
}
