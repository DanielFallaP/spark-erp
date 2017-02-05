package co.com.cybersoft.maintenance.tables.persistence.services.effectFailTechnicalAction;

import co.com.cybersoft.maintenance.tables.persistence.repository.effectFailTechnicalAction.EffectFailTechnicalActionRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class EffectFailTechnicalActionPersistenceFactory {

	EffectFailTechnicalActionRepository effectFailTechnicalActionRepository;
	
	public EffectFailTechnicalActionPersistenceFactory(EffectFailTechnicalActionRepository effectFailTechnicalActionRepository){
		this.effectFailTechnicalActionRepository=effectFailTechnicalActionRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.EffectFailTechnicalAction getEffectFailTechnicalActionByField(String field, String value){
		
		return null;
	}
}