package co.com.cybersoft.maintenance.tables.persistence.services.physicalLocation;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.PhysicalLocationDetails;
import co.com.cybersoft.maintenance.tables.events.physicalLocation.CreatePhysicalLocationEvent;
import co.com.cybersoft.maintenance.tables.events.physicalLocation.PhysicalLocationDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.physicalLocation.PhysicalLocationPageEvent;
import co.com.cybersoft.maintenance.tables.events.physicalLocation.PhysicalLocationModificationEvent;
import co.com.cybersoft.maintenance.tables.events.physicalLocation.RequestPhysicalLocationDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.physicalLocation.RequestPhysicalLocationPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.PhysicalLocation;
import co.com.cybersoft.maintenance.tables.persistence.repository.physicalLocation.PhysicalLocationRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.physicalLocation.PhysicalLocationCustomRepo;
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
public class PhysicalLocationPersistenceServiceImpl implements PhysicalLocationPersistenceService{

	private final PhysicalLocationRepository physicalLocationRepository;
	
	private final PhysicalLocationCustomRepo physicalLocationCustomRepo;
	
	
	public PhysicalLocationPersistenceServiceImpl(final PhysicalLocationRepository physicalLocationRepository, final PhysicalLocationCustomRepo physicalLocationCustomRepo) {
		this.physicalLocationRepository=physicalLocationRepository;
		this.physicalLocationCustomRepo=physicalLocationCustomRepo;
	}
	
	public PhysicalLocationDetailsEvent createPhysicalLocation(CreatePhysicalLocationEvent event) throws Exception{
		PhysicalLocation physicalLocation = new PhysicalLocation().fromPhysicalLocationDetails(event.getPhysicalLocationDetails());
		physicalLocation = physicalLocationRepository.save(physicalLocation);
		physicalLocation = physicalLocationRepository.findOne(physicalLocation.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",physicalLocation.getId());
		return new PhysicalLocationDetailsEvent(new PhysicalLocationDetails().toPhysicalLocationDetails(physicalLocation));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"physicalLocation", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public PhysicalLocationPageEvent requestPhysicalLocationPage(RequestPhysicalLocationPageEvent event) throws Exception {
		Page<PhysicalLocation> physicalLocations = physicalLocationRepository.findAll(event.getPageable());
		return new PhysicalLocationPageEvent(physicalLocations);
	}

	public PhysicalLocationDetailsEvent requestPhysicalLocationDetails(RequestPhysicalLocationDetailsEvent event) throws Exception {
		PhysicalLocationDetails physicalLocationDetails=null;
		if (event.getId()!=null){
			PhysicalLocation physicalLocation = physicalLocationRepository.findOne(event.getId());
			if (physicalLocation!=null)
				physicalLocationDetails = new PhysicalLocationDetails().toPhysicalLocationDetails(physicalLocation);
		}
		return new PhysicalLocationDetailsEvent(physicalLocationDetails);
		
	}

	public PhysicalLocationDetailsEvent modifyPhysicalLocation(PhysicalLocationModificationEvent event) throws Exception {
		PhysicalLocation physicalLocation = new PhysicalLocation().fromPhysicalLocationDetails(event.getPhysicalLocationDetails());
		physicalLocation = physicalLocationRepository.save(physicalLocation);
		physicalLocation = physicalLocationRepository.findOne(physicalLocation.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",physicalLocation.getId());
		return new PhysicalLocationDetailsEvent(new PhysicalLocationDetails().toPhysicalLocationDetails(physicalLocation));
	}
	
		public PhysicalLocationDetailsEvent getOnlyRecord() throws Exception {
			Iterable<PhysicalLocation> all = physicalLocationRepository.findAll();
			PhysicalLocationDetails single = new PhysicalLocationDetails();
			for (PhysicalLocation physicalLocation : all) {
				single=new PhysicalLocationDetails().toPhysicalLocationDetails(physicalLocation);
				break;
			}
			return new PhysicalLocationDetailsEvent(single);
		}
	
	public PhysicalLocationPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<PhysicalLocation> all = physicalLocationCustomRepo.findAllActiveByCompany(fields);
			List<PhysicalLocationDetails> list = new ArrayList<PhysicalLocationDetails>();
			for (PhysicalLocation physicalLocation : all) {
				list.add(new PhysicalLocationDetails().toPhysicalLocationDetails(physicalLocation, fields));
			}
			return new PhysicalLocationPageEvent(list);
		}public PhysicalLocationPageEvent requestAllByNamePhysicalLocation(EmbeddedField... fields) throws Exception {
			List<PhysicalLocation> all = physicalLocationCustomRepo.findAllActiveByNamePhysicalLocation(fields);
			List<PhysicalLocationDetails> list = new ArrayList<PhysicalLocationDetails>();
			for (PhysicalLocation physicalLocation : all) {
				list.add(new PhysicalLocationDetails().toPhysicalLocationDetails(physicalLocation, fields));
			}
			return new PhysicalLocationPageEvent(list);
		}public PhysicalLocationPageEvent requestAllByDescription(EmbeddedField... fields) throws Exception {
			List<PhysicalLocation> all = physicalLocationCustomRepo.findAllActiveByDescription(fields);
			List<PhysicalLocationDetails> list = new ArrayList<PhysicalLocationDetails>();
			for (PhysicalLocation physicalLocation : all) {
				list.add(new PhysicalLocationDetails().toPhysicalLocationDetails(physicalLocation, fields));
			}
			return new PhysicalLocationPageEvent(list);
		}public PhysicalLocationPageEvent requestAllByDescriptionEnglish(EmbeddedField... fields) throws Exception {
			List<PhysicalLocation> all = physicalLocationCustomRepo.findAllActiveByDescriptionEnglish(fields);
			List<PhysicalLocationDetails> list = new ArrayList<PhysicalLocationDetails>();
			for (PhysicalLocation physicalLocation : all) {
				list.add(new PhysicalLocationDetails().toPhysicalLocationDetails(physicalLocation, fields));
			}
			return new PhysicalLocationPageEvent(list);
		}public PhysicalLocationPageEvent requestAllByDescriptionShort(EmbeddedField... fields) throws Exception {
			List<PhysicalLocation> all = physicalLocationCustomRepo.findAllActiveByDescriptionShort(fields);
			List<PhysicalLocationDetails> list = new ArrayList<PhysicalLocationDetails>();
			for (PhysicalLocation physicalLocation : all) {
				list.add(new PhysicalLocationDetails().toPhysicalLocationDetails(physicalLocation, fields));
			}
			return new PhysicalLocationPageEvent(list);
		}public PhysicalLocationPageEvent requestAllByMeasurementUnit(EmbeddedField... fields) throws Exception {
			List<PhysicalLocation> all = physicalLocationCustomRepo.findAllActiveByMeasurementUnit(fields);
			List<PhysicalLocationDetails> list = new ArrayList<PhysicalLocationDetails>();
			for (PhysicalLocation physicalLocation : all) {
				list.add(new PhysicalLocationDetails().toPhysicalLocationDetails(physicalLocation, fields));
			}
			return new PhysicalLocationPageEvent(list);
		}public PhysicalLocationPageEvent requestAllByCapacityPhysicalLocation(EmbeddedField... fields) throws Exception {
			List<PhysicalLocation> all = physicalLocationCustomRepo.findAllActiveByCapacityPhysicalLocation(fields);
			List<PhysicalLocationDetails> list = new ArrayList<PhysicalLocationDetails>();
			for (PhysicalLocation physicalLocation : all) {
				list.add(new PhysicalLocationDetails().toPhysicalLocationDetails(physicalLocation, fields));
			}
			return new PhysicalLocationPageEvent(list);
		}
	

	public PhysicalLocationPageEvent requestPhysicalLocationFilterPage(RequestPhysicalLocationPageEvent event) throws Exception {
		Page<PhysicalLocation> page = physicalLocationCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new PhysicalLocationPageEvent(page, physicalLocationCustomRepo.getTotalCount());
	}
	
	public PhysicalLocationPageEvent requestPhysicalLocationFilter(
			RequestPhysicalLocationPageEvent event) throws Exception {
		List<PhysicalLocation> all = physicalLocationCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		PhysicalLocationPageEvent pageEvent = new PhysicalLocationPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}