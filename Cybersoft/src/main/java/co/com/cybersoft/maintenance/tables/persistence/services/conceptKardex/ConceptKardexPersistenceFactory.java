package co.com.cybersoft.maintenance.tables.persistence.services.conceptKardex;

import co.com.cybersoft.maintenance.tables.persistence.repository.conceptKardex.ConceptKardexRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ConceptKardexPersistenceFactory {

	ConceptKardexRepository conceptKardexRepository;
	
	public ConceptKardexPersistenceFactory(ConceptKardexRepository conceptKardexRepository){
		this.conceptKardexRepository=conceptKardexRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.ConceptKardex getConceptKardexByField(String field, String value){
		
		return null;
	}
}