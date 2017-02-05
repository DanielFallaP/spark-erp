package co.com.cybersoft.maintenance.tables.persistence.services.typeConceptKardex;

import co.com.cybersoft.maintenance.tables.persistence.repository.typeConceptKardex.TypeConceptKardexRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class TypeConceptKardexPersistenceFactory {

	TypeConceptKardexRepository typeConceptKardexRepository;
	
	public TypeConceptKardexPersistenceFactory(TypeConceptKardexRepository typeConceptKardexRepository){
		this.typeConceptKardexRepository=typeConceptKardexRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.TypeConceptKardex getTypeConceptKardexByField(String field, String value){
		if (field.equals("typeConceptKardex"))
					return typeConceptKardexRepository.findByTypeConceptKardex(value);		
		return null;
	}
}