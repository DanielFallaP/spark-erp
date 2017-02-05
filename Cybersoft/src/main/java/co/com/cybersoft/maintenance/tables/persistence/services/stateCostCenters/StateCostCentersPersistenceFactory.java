package co.com.cybersoft.maintenance.tables.persistence.services.stateCostCenters;

import co.com.cybersoft.maintenance.tables.persistence.repository.stateCostCenters.StateCostCentersRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class StateCostCentersPersistenceFactory {

	StateCostCentersRepository stateCostCentersRepository;
	
	public StateCostCentersPersistenceFactory(StateCostCentersRepository stateCostCentersRepository){
		this.stateCostCentersRepository=stateCostCentersRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.StateCostCenters getStateCostCentersByField(String field, String value){
		if (field.equals("stateCostCenters"))
					return stateCostCentersRepository.findByStateCostCenters(value);		
		return null;
	}
}