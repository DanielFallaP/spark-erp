package co.com.cybersoft.maintenance.tables.persistence.services.characteristic;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.CharacteristicDetails;
import co.com.cybersoft.maintenance.tables.events.characteristic.CreateCharacteristicEvent;
import co.com.cybersoft.maintenance.tables.events.characteristic.CharacteristicDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.characteristic.CharacteristicPageEvent;
import co.com.cybersoft.maintenance.tables.events.characteristic.CharacteristicModificationEvent;
import co.com.cybersoft.maintenance.tables.events.characteristic.RequestCharacteristicDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.characteristic.RequestCharacteristicPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Characteristic;
import co.com.cybersoft.maintenance.tables.persistence.repository.characteristic.CharacteristicRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.characteristic.CharacteristicCustomRepo;
//import co.com.cybersoft.man.services.security.SessionAction;
//import co.com.cybersoft.man.services.security.SessionLogger;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CharacteristicPersistenceServiceImpl implements CharacteristicPersistenceService{

	private final CharacteristicRepository characteristicRepository;
	
	private final CharacteristicCustomRepo characteristicCustomRepo;
	
	
	public CharacteristicPersistenceServiceImpl(final CharacteristicRepository characteristicRepository, final CharacteristicCustomRepo characteristicCustomRepo) {
		this.characteristicRepository=characteristicRepository;
		this.characteristicCustomRepo=characteristicCustomRepo;
	}
	
	public CharacteristicDetailsEvent createCharacteristic(CreateCharacteristicEvent event) throws Exception{
		Characteristic characteristic = new Characteristic().fromCharacteristicDetails(event.getCharacteristicDetails());
		characteristic = characteristicRepository.save(characteristic);
		characteristic = characteristicRepository.findOne(characteristic.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",characteristic.getId());
		return new CharacteristicDetailsEvent(new CharacteristicDetails().toCharacteristicDetails(characteristic));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"characteristic", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public CharacteristicPageEvent requestCharacteristicPage(RequestCharacteristicPageEvent event) throws Exception {
		Page<Characteristic> characteristics = characteristicRepository.findAll(event.getPageable());
		return new CharacteristicPageEvent(characteristics);
	}

	public CharacteristicDetailsEvent requestCharacteristicDetails(RequestCharacteristicDetailsEvent event) throws Exception {
		CharacteristicDetails characteristicDetails=null;
		if (event.getId()!=null){
			Characteristic characteristic = characteristicRepository.findOne(event.getId());
			if (characteristic!=null)
				characteristicDetails = new CharacteristicDetails().toCharacteristicDetails(characteristic);
		}
		return new CharacteristicDetailsEvent(characteristicDetails);
		
	}

	public CharacteristicDetailsEvent modifyCharacteristic(CharacteristicModificationEvent event) throws Exception {
		Characteristic characteristic = new Characteristic().fromCharacteristicDetails(event.getCharacteristicDetails());
		characteristic = characteristicRepository.save(characteristic);
		characteristic = characteristicRepository.findOne(characteristic.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",characteristic.getId());
		return new CharacteristicDetailsEvent(new CharacteristicDetails().toCharacteristicDetails(characteristic));
	}
	
		public CharacteristicDetailsEvent getOnlyRecord() throws Exception {
			Iterable<Characteristic> all = characteristicRepository.findAll();
			CharacteristicDetails single = new CharacteristicDetails();
			for (Characteristic characteristic : all) {
				single=new CharacteristicDetails().toCharacteristicDetails(characteristic);
				break;
			}
			return new CharacteristicDetailsEvent(single);
		}
	
	public CharacteristicPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<Characteristic> all = characteristicCustomRepo.findAllActiveByCompany(fields);
			List<CharacteristicDetails> list = new ArrayList<CharacteristicDetails>();
			for (Characteristic characteristic : all) {
				list.add(new CharacteristicDetails().toCharacteristicDetails(characteristic, fields));
			}
			return new CharacteristicPageEvent(list);
		}public CharacteristicPageEvent requestAllByNameCharacteristic(EmbeddedField... fields) throws Exception {
			List<Characteristic> all = characteristicCustomRepo.findAllActiveByNameCharacteristic(fields);
			List<CharacteristicDetails> list = new ArrayList<CharacteristicDetails>();
			for (Characteristic characteristic : all) {
				list.add(new CharacteristicDetails().toCharacteristicDetails(characteristic, fields));
			}
			return new CharacteristicPageEvent(list);
		}
	

	public CharacteristicPageEvent requestCharacteristicFilterPage(RequestCharacteristicPageEvent event) throws Exception {
		Page<Characteristic> page = characteristicCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new CharacteristicPageEvent(page, characteristicCustomRepo.getTotalCount());
	}
	
	public CharacteristicPageEvent requestCharacteristicFilter(
			RequestCharacteristicPageEvent event) throws Exception {
		List<Characteristic> all = characteristicCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		CharacteristicPageEvent pageEvent = new CharacteristicPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}