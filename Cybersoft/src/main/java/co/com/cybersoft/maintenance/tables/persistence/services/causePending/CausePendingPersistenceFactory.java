package co.com.cybersoft.maintenance.tables.persistence.services.causePending;

import co.com.cybersoft.maintenance.tables.persistence.repository.causePending.CausePendingRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CausePendingPersistenceFactory {

	CausePendingRepository causePendingRepository;
	
	public CausePendingPersistenceFactory(CausePendingRepository causePendingRepository){
		this.causePendingRepository=causePendingRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.CausePending getCausePendingByField(String field, String value){
		if (field.equals("nameCausePending"))
					return causePendingRepository.findByNameCausePending(value);		
		return null;
	}
}