package co.com.cybersoft.maintenance.tables.persistence.services.reference;

import co.com.cybersoft.maintenance.tables.persistence.repository.reference.ReferenceRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ReferencePersistenceFactory {

	ReferenceRepository referenceRepository;
	
	public ReferencePersistenceFactory(ReferenceRepository referenceRepository){
		this.referenceRepository=referenceRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.Reference getReferenceByField(String field, String value){
		if (field.equals("codeReference"))
					return referenceRepository.findByCodeReference(value);if (field.equals("nameReference"))
					return referenceRepository.findByNameReference(value);		
		return null;
	}
}