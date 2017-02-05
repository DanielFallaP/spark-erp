package co.com.cybersoft.maintenance.tables.persistence.services.characteristic;

import co.com.cybersoft.maintenance.tables.events.characteristic.CreateCharacteristicEvent;
import co.com.cybersoft.maintenance.tables.events.characteristic.CharacteristicDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.characteristic.CharacteristicPageEvent;
import co.com.cybersoft.maintenance.tables.events.characteristic.CharacteristicModificationEvent;
import co.com.cybersoft.maintenance.tables.events.characteristic.RequestCharacteristicDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.characteristic.RequestCharacteristicPageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface CharacteristicPersistenceService {

	CharacteristicDetailsEvent createCharacteristic(CreateCharacteristicEvent event) throws Exception;

	CharacteristicPageEvent requestCharacteristicPage(RequestCharacteristicPageEvent event) throws Exception;

	CharacteristicDetailsEvent requestCharacteristicDetails(RequestCharacteristicDetailsEvent event) throws Exception;
	
	CharacteristicDetailsEvent modifyCharacteristic(CharacteristicModificationEvent event) throws Exception;
	CharacteristicPageEvent requestCharacteristicFilterPage(RequestCharacteristicPageEvent event) throws Exception;
	CharacteristicPageEvent requestCharacteristicFilter(RequestCharacteristicPageEvent event) throws Exception;
	
	CharacteristicPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	CharacteristicPageEvent requestAllByNameCharacteristic(EmbeddedField... fields) throws Exception;

	
	
	CharacteristicDetailsEvent getOnlyRecord() throws Exception;
	
}