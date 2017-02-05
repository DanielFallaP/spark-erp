package co.com.cybersoft.maintenance.tables.persistence.services.typeWork;

import co.com.cybersoft.maintenance.tables.persistence.repository.typeWork.TypeWorkRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class TypeWorkPersistenceFactory {

	TypeWorkRepository typeWorkRepository;
	
	public TypeWorkPersistenceFactory(TypeWorkRepository typeWorkRepository){
		this.typeWorkRepository=typeWorkRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.TypeWork getTypeWorkByField(String field, String value){
		if (field.equals("typeWork"))
					return typeWorkRepository.findByTypeWork(value);		
		return null;
	}
}