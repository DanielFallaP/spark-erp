package co.com.cybersoft.maintenance.tables.persistence.services.technicalAction;

import co.com.cybersoft.maintenance.tables.persistence.repository.technicalAction.TechnicalActionRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class TechnicalActionPersistenceFactory {

	TechnicalActionRepository technicalActionRepository;
	
	public TechnicalActionPersistenceFactory(TechnicalActionRepository technicalActionRepository){
		this.technicalActionRepository=technicalActionRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.TechnicalAction getTechnicalActionByField(String field, String value){
		if (field.equals("code"))
					return technicalActionRepository.findByCode(value);if (field.equals("technicalActionName"))
					return technicalActionRepository.findByTechnicalActionName(value);		
		return null;
	}
}