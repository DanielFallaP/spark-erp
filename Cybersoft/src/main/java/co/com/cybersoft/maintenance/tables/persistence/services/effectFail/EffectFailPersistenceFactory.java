package co.com.cybersoft.maintenance.tables.persistence.services.effectFail;

import co.com.cybersoft.maintenance.tables.persistence.repository.effectFail.EffectFailRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class EffectFailPersistenceFactory {

	EffectFailRepository effectFailRepository;
	
	public EffectFailPersistenceFactory(EffectFailRepository effectFailRepository){
		this.effectFailRepository=effectFailRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.EffectFail getEffectFailByField(String field, String value){
		if (field.equals("effectFailName"))
					return effectFailRepository.findByEffectFailName(value);		
		return null;
	}
}