package co.com.cybersoft.maintenance.tables.persistence.services.responsibleCenter;

import co.com.cybersoft.maintenance.tables.persistence.repository.responsibleCenter.ResponsibleCenterRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ResponsibleCenterPersistenceFactory {

	ResponsibleCenterRepository responsibleCenterRepository;
	
	public ResponsibleCenterPersistenceFactory(ResponsibleCenterRepository responsibleCenterRepository){
		this.responsibleCenterRepository=responsibleCenterRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.ResponsibleCenter getResponsibleCenterByField(String field, String value){
		if (field.equals("nameResponsibleCenter"))
					return responsibleCenterRepository.findByNameResponsibleCenter(value);		
		return null;
	}
}