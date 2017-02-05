package co.com.cybersoft.maintenance.tables.events.characteristic;

import co.com.cybersoft.maintenance.tables.core.domain.CharacteristicDetails;

/**
 * Event class for Characteristic
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CharacteristicModificationEvent {

	private CharacteristicDetails characteristicDetails;
	
	public CharacteristicModificationEvent(CharacteristicDetails characteristicDetails){
		this.characteristicDetails=characteristicDetails;
	}

	public CharacteristicDetails getCharacteristicDetails() {
		return characteristicDetails;
	}
	
}