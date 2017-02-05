package co.com.cybersoft.maintenance.tables.core.services.characteristic;

import co.com.cybersoft.maintenance.tables.events.characteristic.CreateCharacteristicEvent;
import co.com.cybersoft.maintenance.tables.events.characteristic.CharacteristicDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.characteristic.CharacteristicPageEvent;
import co.com.cybersoft.maintenance.tables.events.characteristic.CharacteristicModificationEvent;
import co.com.cybersoft.maintenance.tables.events.characteristic.RequestCharacteristicDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.characteristic.RequestCharacteristicPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.characteristic.CharacteristicPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CharacteristicServiceImpl implements CharacteristicService{

	private final CharacteristicPersistenceService characteristicPersistenceService;
	
	public CharacteristicServiceImpl(final CharacteristicPersistenceService characteristicPersistenceService){
		this.characteristicPersistenceService=characteristicPersistenceService;
	}
	
	/**
	 */
	public CharacteristicDetailsEvent createCharacteristic(CreateCharacteristicEvent event ) throws Exception{
		return characteristicPersistenceService.createCharacteristic(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public CharacteristicPageEvent requestCharacteristicPage(RequestCharacteristicPageEvent event) throws Exception{
		return characteristicPersistenceService.requestCharacteristicPage(event);
	}

	public CharacteristicDetailsEvent requestCharacteristicDetails(RequestCharacteristicDetailsEvent event ) throws Exception{
		return characteristicPersistenceService.requestCharacteristicDetails(event);
	}

	public CharacteristicDetailsEvent modifyCharacteristic(CharacteristicModificationEvent event) throws Exception {
		return characteristicPersistenceService.modifyCharacteristic(event);
	}
	
	public CharacteristicDetailsEvent requestOnlyRecord() throws Exception {
		return characteristicPersistenceService.getOnlyRecord();
	}
	
	public CharacteristicPageEvent requestCharacteristicFilterPage(RequestCharacteristicPageEvent event) throws Exception {
		return characteristicPersistenceService.requestCharacteristicFilterPage(event);
	}
	
	public CharacteristicPageEvent requestCharacteristicFilter(RequestCharacteristicPageEvent event) throws Exception {
		return characteristicPersistenceService.requestCharacteristicFilter(event);
	}
	

	public CharacteristicPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return characteristicPersistenceService.requestAllByCompany(fields);
	}public CharacteristicPageEvent requestAllByNameCharacteristic(EmbeddedField... fields) throws Exception {
		return characteristicPersistenceService.requestAllByNameCharacteristic(fields);
	}
	
	
	
}