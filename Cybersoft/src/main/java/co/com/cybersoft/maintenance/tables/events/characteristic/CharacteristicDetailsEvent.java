package co.com.cybersoft.maintenance.tables.events.characteristic;

import co.com.cybersoft.maintenance.tables.core.domain.CharacteristicDetails;

/**
 * Event class for Characteristic
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CharacteristicDetailsEvent {
	
	private CharacteristicDetails characteristicDetails;
	
	public CharacteristicDetailsEvent(CharacteristicDetails characteristicDetails){
		this.characteristicDetails=characteristicDetails;
	}

	public CharacteristicDetails getCharacteristicDetails() {
		return characteristicDetails;
	}

}