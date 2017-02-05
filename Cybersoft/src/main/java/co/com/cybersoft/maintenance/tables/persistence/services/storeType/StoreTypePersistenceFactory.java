package co.com.cybersoft.maintenance.tables.persistence.services.storeType;

import co.com.cybersoft.maintenance.tables.persistence.repository.storeType.StoreTypeRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class StoreTypePersistenceFactory {

	StoreTypeRepository storeTypeRepository;
	
	public StoreTypePersistenceFactory(StoreTypeRepository storeTypeRepository){
		this.storeTypeRepository=storeTypeRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.StoreType getStoreTypeByField(String field, String value){
		if (field.equals("storeName"))
					return storeTypeRepository.findByStoreName(value);		
		return null;
	}
}