package co.com.cybersoft.maintenance.tables.persistence.services.otherConcepts;

import co.com.cybersoft.maintenance.tables.persistence.repository.otherConcepts.OtherConceptsRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class OtherConceptsPersistenceFactory {

	OtherConceptsRepository otherConceptsRepository;
	
	public OtherConceptsPersistenceFactory(OtherConceptsRepository otherConceptsRepository){
		this.otherConceptsRepository=otherConceptsRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.OtherConcepts getOtherConceptsByField(String field, String value){
		if (field.equals("nameOtherconcepts"))
					return otherConceptsRepository.findByNameOtherconcepts(value);		
		return null;
	}
}