package co.com.cybersoft.maintenance.tables.persistence.services.costingType;

import co.com.cybersoft.maintenance.tables.persistence.repository.costingType.CostingTypeRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CostingTypePersistenceFactory {

	CostingTypeRepository costingTypeRepository;
	
	public CostingTypePersistenceFactory(CostingTypeRepository costingTypeRepository){
		this.costingTypeRepository=costingTypeRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.CostingType getCostingTypeByField(String field, String value){
		if (field.equals("costingType"))
					return costingTypeRepository.findByCostingType(value);		
		return null;
	}
}