package co.com.cybersoft.maintenance.tables.persistence.services.characteristic;

import co.com.cybersoft.maintenance.tables.persistence.repository.characteristic.CharacteristicRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CharacteristicPersistenceFactory {

	CharacteristicRepository characteristicRepository;
	
	public CharacteristicPersistenceFactory(CharacteristicRepository characteristicRepository){
		this.characteristicRepository=characteristicRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.Characteristic getCharacteristicByField(String field, String value){
		if (field.equals("nameCharacteristic"))
					return characteristicRepository.findByNameCharacteristic(value);		
		return null;
	}
}