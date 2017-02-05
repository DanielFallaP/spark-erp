package co.com.cybersoft.maintenance.tables.persistence.services.third;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.ThirdDetails;
import co.com.cybersoft.maintenance.tables.events.third.CreateThirdEvent;
import co.com.cybersoft.maintenance.tables.events.third.ThirdDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.third.ThirdPageEvent;
import co.com.cybersoft.maintenance.tables.events.third.ThirdModificationEvent;
import co.com.cybersoft.maintenance.tables.events.third.RequestThirdDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.third.RequestThirdPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Third;
import co.com.cybersoft.maintenance.tables.persistence.repository.third.ThirdRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.third.ThirdCustomRepo;
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
public class ThirdPersistenceServiceImpl implements ThirdPersistenceService{

	private final ThirdRepository thirdRepository;
	
	private final ThirdCustomRepo thirdCustomRepo;
	
	
	public ThirdPersistenceServiceImpl(final ThirdRepository thirdRepository, final ThirdCustomRepo thirdCustomRepo) {
		this.thirdRepository=thirdRepository;
		this.thirdCustomRepo=thirdCustomRepo;
	}
	
	public ThirdDetailsEvent createThird(CreateThirdEvent event) throws Exception{
		Third third = new Third().fromThirdDetails(event.getThirdDetails());
		third = thirdRepository.save(third);
		third = thirdRepository.findOne(third.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",third.getId());
		return new ThirdDetailsEvent(new ThirdDetails().toThirdDetails(third));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"third", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public ThirdPageEvent requestThirdPage(RequestThirdPageEvent event) throws Exception {
		Page<Third> thirds = thirdRepository.findAll(event.getPageable());
		return new ThirdPageEvent(thirds);
	}

	public ThirdDetailsEvent requestThirdDetails(RequestThirdDetailsEvent event) throws Exception {
		ThirdDetails thirdDetails=null;
		if (event.getId()!=null){
			Third third = thirdRepository.findOne(event.getId());
			if (third!=null)
				thirdDetails = new ThirdDetails().toThirdDetails(third);
		}
		return new ThirdDetailsEvent(thirdDetails);
		
	}

	public ThirdDetailsEvent modifyThird(ThirdModificationEvent event) throws Exception {
		Third third = new Third().fromThirdDetails(event.getThirdDetails());
		third = thirdRepository.save(third);
		third = thirdRepository.findOne(third.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",third.getId());
		return new ThirdDetailsEvent(new ThirdDetails().toThirdDetails(third));
	}
	
		public ThirdDetailsEvent getOnlyRecord() throws Exception {
			Iterable<Third> all = thirdRepository.findAll();
			ThirdDetails single = new ThirdDetails();
			for (Third third : all) {
				single=new ThirdDetails().toThirdDetails(third);
				break;
			}
			return new ThirdDetailsEvent(single);
		}
	
	public ThirdPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<Third> all = thirdCustomRepo.findAllActiveByCompany(fields);
			List<ThirdDetails> list = new ArrayList<ThirdDetails>();
			for (Third third : all) {
				list.add(new ThirdDetails().toThirdDetails(third, fields));
			}
			return new ThirdPageEvent(list);
		}public ThirdPageEvent requestAllByCostCenter(EmbeddedField... fields) throws Exception {
			List<Third> all = thirdCustomRepo.findAllActiveByCostCenter(fields);
			List<ThirdDetails> list = new ArrayList<ThirdDetails>();
			for (Third third : all) {
				list.add(new ThirdDetails().toThirdDetails(third, fields));
			}
			return new ThirdPageEvent(list);
		}public ThirdPageEvent requestAllByCode(EmbeddedField... fields) throws Exception {
			List<Third> all = thirdCustomRepo.findAllActiveByCode(fields);
			List<ThirdDetails> list = new ArrayList<ThirdDetails>();
			for (Third third : all) {
				list.add(new ThirdDetails().toThirdDetails(third, fields));
			}
			return new ThirdPageEvent(list);
		}public ThirdPageEvent requestAllByNameThird(EmbeddedField... fields) throws Exception {
			List<Third> all = thirdCustomRepo.findAllActiveByNameThird(fields);
			List<ThirdDetails> list = new ArrayList<ThirdDetails>();
			for (Third third : all) {
				list.add(new ThirdDetails().toThirdDetails(third, fields));
			}
			return new ThirdPageEvent(list);
		}public ThirdPageEvent requestAllByContact(EmbeddedField... fields) throws Exception {
			List<Third> all = thirdCustomRepo.findAllActiveByContact(fields);
			List<ThirdDetails> list = new ArrayList<ThirdDetails>();
			for (Third third : all) {
				list.add(new ThirdDetails().toThirdDetails(third, fields));
			}
			return new ThirdPageEvent(list);
		}public ThirdPageEvent requestAllByAddress(EmbeddedField... fields) throws Exception {
			List<Third> all = thirdCustomRepo.findAllActiveByAddress(fields);
			List<ThirdDetails> list = new ArrayList<ThirdDetails>();
			for (Third third : all) {
				list.add(new ThirdDetails().toThirdDetails(third, fields));
			}
			return new ThirdPageEvent(list);
		}public ThirdPageEvent requestAllByCountry(EmbeddedField... fields) throws Exception {
			List<Third> all = thirdCustomRepo.findAllActiveByCountry(fields);
			List<ThirdDetails> list = new ArrayList<ThirdDetails>();
			for (Third third : all) {
				list.add(new ThirdDetails().toThirdDetails(third, fields));
			}
			return new ThirdPageEvent(list);
		}public ThirdPageEvent requestAllByPhoneOne(EmbeddedField... fields) throws Exception {
			List<Third> all = thirdCustomRepo.findAllActiveByPhoneOne(fields);
			List<ThirdDetails> list = new ArrayList<ThirdDetails>();
			for (Third third : all) {
				list.add(new ThirdDetails().toThirdDetails(third, fields));
			}
			return new ThirdPageEvent(list);
		}public ThirdPageEvent requestAllByPhoneTwo(EmbeddedField... fields) throws Exception {
			List<Third> all = thirdCustomRepo.findAllActiveByPhoneTwo(fields);
			List<ThirdDetails> list = new ArrayList<ThirdDetails>();
			for (Third third : all) {
				list.add(new ThirdDetails().toThirdDetails(third, fields));
			}
			return new ThirdPageEvent(list);
		}public ThirdPageEvent requestAllByEmail(EmbeddedField... fields) throws Exception {
			List<Third> all = thirdCustomRepo.findAllActiveByEmail(fields);
			List<ThirdDetails> list = new ArrayList<ThirdDetails>();
			for (Third third : all) {
				list.add(new ThirdDetails().toThirdDetails(third, fields));
			}
			return new ThirdPageEvent(list);
		}public ThirdPageEvent requestAllByComment(EmbeddedField... fields) throws Exception {
			List<Third> all = thirdCustomRepo.findAllActiveByComment(fields);
			List<ThirdDetails> list = new ArrayList<ThirdDetails>();
			for (Third third : all) {
				list.add(new ThirdDetails().toThirdDetails(third, fields));
			}
			return new ThirdPageEvent(list);
		}public ThirdPageEvent requestAllByTypeRegime(EmbeddedField... fields) throws Exception {
			List<Third> all = thirdCustomRepo.findAllActiveByTypeRegime(fields);
			List<ThirdDetails> list = new ArrayList<ThirdDetails>();
			for (Third third : all) {
				list.add(new ThirdDetails().toThirdDetails(third, fields));
			}
			return new ThirdPageEvent(list);
		}public ThirdPageEvent requestAllByExternalAccess(EmbeddedField... fields) throws Exception {
			List<Third> all = thirdCustomRepo.findAllActiveByExternalAccess(fields);
			List<ThirdDetails> list = new ArrayList<ThirdDetails>();
			for (Third third : all) {
				list.add(new ThirdDetails().toThirdDetails(third, fields));
			}
			return new ThirdPageEvent(list);
		}public ThirdPageEvent requestAllByKeyExternalAccess(EmbeddedField... fields) throws Exception {
			List<Third> all = thirdCustomRepo.findAllActiveByKeyExternalAccess(fields);
			List<ThirdDetails> list = new ArrayList<ThirdDetails>();
			for (Third third : all) {
				list.add(new ThirdDetails().toThirdDetails(third, fields));
			}
			return new ThirdPageEvent(list);
		}
	

	public ThirdPageEvent requestThirdFilterPage(RequestThirdPageEvent event) throws Exception {
		Page<Third> page = thirdCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new ThirdPageEvent(page, thirdCustomRepo.getTotalCount());
	}
	
	public ThirdPageEvent requestThirdFilter(
			RequestThirdPageEvent event) throws Exception {
		List<Third> all = thirdCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		ThirdPageEvent pageEvent = new ThirdPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}