package co.com.cybersoft.maintenance.tables.persistence.services.effectFail;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.EffectFailDetails;
import co.com.cybersoft.maintenance.tables.events.effectFail.CreateEffectFailEvent;
import co.com.cybersoft.maintenance.tables.events.effectFail.EffectFailDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.effectFail.EffectFailPageEvent;
import co.com.cybersoft.maintenance.tables.events.effectFail.EffectFailModificationEvent;
import co.com.cybersoft.maintenance.tables.events.effectFail.RequestEffectFailDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.effectFail.RequestEffectFailPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.EffectFail;
import co.com.cybersoft.maintenance.tables.persistence.repository.effectFail.EffectFailRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.effectFail.EffectFailCustomRepo;
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
public class EffectFailPersistenceServiceImpl implements EffectFailPersistenceService{

	private final EffectFailRepository effectFailRepository;
	
	private final EffectFailCustomRepo effectFailCustomRepo;
	
	
	public EffectFailPersistenceServiceImpl(final EffectFailRepository effectFailRepository, final EffectFailCustomRepo effectFailCustomRepo) {
		this.effectFailRepository=effectFailRepository;
		this.effectFailCustomRepo=effectFailCustomRepo;
	}
	
	public EffectFailDetailsEvent createEffectFail(CreateEffectFailEvent event) throws Exception{
		EffectFail effectFail = new EffectFail().fromEffectFailDetails(event.getEffectFailDetails());
		effectFail = effectFailRepository.save(effectFail);
		effectFail = effectFailRepository.findOne(effectFail.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",effectFail.getId());
		return new EffectFailDetailsEvent(new EffectFailDetails().toEffectFailDetails(effectFail));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"effectFail", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public EffectFailPageEvent requestEffectFailPage(RequestEffectFailPageEvent event) throws Exception {
		Page<EffectFail> effectFails = effectFailRepository.findAll(event.getPageable());
		return new EffectFailPageEvent(effectFails);
	}

	public EffectFailDetailsEvent requestEffectFailDetails(RequestEffectFailDetailsEvent event) throws Exception {
		EffectFailDetails effectFailDetails=null;
		if (event.getId()!=null){
			EffectFail effectFail = effectFailRepository.findOne(event.getId());
			if (effectFail!=null)
				effectFailDetails = new EffectFailDetails().toEffectFailDetails(effectFail);
		}
		return new EffectFailDetailsEvent(effectFailDetails);
		
	}

	public EffectFailDetailsEvent modifyEffectFail(EffectFailModificationEvent event) throws Exception {
		EffectFail effectFail = new EffectFail().fromEffectFailDetails(event.getEffectFailDetails());
		effectFail = effectFailRepository.save(effectFail);
		effectFail = effectFailRepository.findOne(effectFail.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",effectFail.getId());
		return new EffectFailDetailsEvent(new EffectFailDetails().toEffectFailDetails(effectFail));
	}
	
		public EffectFailDetailsEvent getOnlyRecord() throws Exception {
			Iterable<EffectFail> all = effectFailRepository.findAll();
			EffectFailDetails single = new EffectFailDetails();
			for (EffectFail effectFail : all) {
				single=new EffectFailDetails().toEffectFailDetails(effectFail);
				break;
			}
			return new EffectFailDetailsEvent(single);
		}
	
	public EffectFailPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<EffectFail> all = effectFailCustomRepo.findAllActiveByCompany(fields);
			List<EffectFailDetails> list = new ArrayList<EffectFailDetails>();
			for (EffectFail effectFail : all) {
				list.add(new EffectFailDetails().toEffectFailDetails(effectFail, fields));
			}
			return new EffectFailPageEvent(list);
		}public EffectFailPageEvent requestAllByEffectFailName(EmbeddedField... fields) throws Exception {
			List<EffectFail> all = effectFailCustomRepo.findAllActiveByEffectFailName(fields);
			List<EffectFailDetails> list = new ArrayList<EffectFailDetails>();
			for (EffectFail effectFail : all) {
				list.add(new EffectFailDetails().toEffectFailDetails(effectFail, fields));
			}
			return new EffectFailPageEvent(list);
		}
	

	public EffectFailPageEvent requestEffectFailFilterPage(RequestEffectFailPageEvent event) throws Exception {
		Page<EffectFail> page = effectFailCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new EffectFailPageEvent(page, effectFailCustomRepo.getTotalCount());
	}
	
	public EffectFailPageEvent requestEffectFailFilter(
			RequestEffectFailPageEvent event) throws Exception {
		List<EffectFail> all = effectFailCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		EffectFailPageEvent pageEvent = new EffectFailPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}