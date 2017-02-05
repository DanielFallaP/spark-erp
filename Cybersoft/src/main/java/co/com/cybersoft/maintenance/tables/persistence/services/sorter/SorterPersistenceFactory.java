package co.com.cybersoft.maintenance.tables.persistence.services.sorter;

import co.com.cybersoft.maintenance.tables.persistence.repository.sorter.SorterRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class SorterPersistenceFactory {

	SorterRepository sorterRepository;
	
	public SorterPersistenceFactory(SorterRepository sorterRepository){
		this.sorterRepository=sorterRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.Sorter getSorterByField(String field, String value){
		if (field.equals("nameSorter"))
					return sorterRepository.findByNameSorter(value);		
		return null;
	}
}