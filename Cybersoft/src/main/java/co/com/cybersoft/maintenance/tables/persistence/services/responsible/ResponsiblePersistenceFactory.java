package co.com.cybersoft.maintenance.tables.persistence.services.responsible;

import co.com.cybersoft.maintenance.tables.persistence.repository.responsible.ResponsibleRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ResponsiblePersistenceFactory {

	ResponsibleRepository responsibleRepository;
	
	public ResponsiblePersistenceFactory(ResponsibleRepository responsibleRepository){
		this.responsibleRepository=responsibleRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.Responsible getResponsibleByField(String field, String value){
		
		return null;
	}
}