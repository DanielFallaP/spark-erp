package co.com.cybersoft.maintenance.tables.persistence.services.typeSorter;

import co.com.cybersoft.maintenance.tables.persistence.repository.typeSorter.TypeSorterRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class TypeSorterPersistenceFactory {

	TypeSorterRepository typeSorterRepository;
	
	public TypeSorterPersistenceFactory(TypeSorterRepository typeSorterRepository){
		this.typeSorterRepository=typeSorterRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.TypeSorter getTypeSorterByField(String field, String value){
		if (field.equals("nameTypeSorter"))
					return typeSorterRepository.findByNameTypeSorter(value);		
		return null;
	}
}