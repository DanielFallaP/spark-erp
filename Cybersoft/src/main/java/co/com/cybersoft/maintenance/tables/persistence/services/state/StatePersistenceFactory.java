package co.com.cybersoft.maintenance.tables.persistence.services.state;

import co.com.cybersoft.maintenance.tables.persistence.repository.state.StateRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class StatePersistenceFactory {

	StateRepository stateRepository;
	
	public StatePersistenceFactory(StateRepository stateRepository){
		this.stateRepository=stateRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.State getStateByField(String field, String value){
		if (field.equals("nameState"))
					return stateRepository.findByNameState(value);		
		return null;
	}
}