package co.com.cybersoft.maintenance.tables.persistence.services.effectFailTechnicalAction;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.EffectFailTechnicalActionDetails;
import co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction.CreateEffectFailTechnicalActionEvent;
import co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction.EffectFailTechnicalActionDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction.EffectFailTechnicalActionPageEvent;
import co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction.EffectFailTechnicalActionModificationEvent;
import co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction.RequestEffectFailTechnicalActionDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction.RequestEffectFailTechnicalActionPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.EffectFailTechnicalAction;
import co.com.cybersoft.maintenance.tables.persistence.repository.effectFailTechnicalAction.EffectFailTechnicalActionRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.effectFailTechnicalAction.EffectFailTechnicalActionCustomRepo;
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
public class EffectFailTechnicalActionPersistenceServiceImpl implements EffectFailTechnicalActionPersistenceService{

	private final EffectFailTechnicalActionRepository effectFailTechnicalActionRepository;
	
	private final EffectFailTechnicalActionCustomRepo effectFailTechnicalActionCustomRepo;
	
	
	public EffectFailTechnicalActionPersistenceServiceImpl(final EffectFailTechnicalActionRepository effectFailTechnicalActionRepository, final EffectFailTechnicalActionCustomRepo effectFailTechnicalActionCustomRepo) {
		this.effectFailTechnicalActionRepository=effectFailTechnicalActionRepository;
		this.effectFailTechnicalActionCustomRepo=effectFailTechnicalActionCustomRepo;
	}
	
	public EffectFailTechnicalActionDetailsEvent createEffectFailTechnicalAction(CreateEffectFailTechnicalActionEvent event) throws Exception{
		EffectFailTechnicalAction effectFailTechnicalAction = new EffectFailTechnicalAction().fromEffectFailTechnicalActionDetails(event.getEffectFailTechnicalActionDetails());
		effectFailTechnicalAction = effectFailTechnicalActionRepository.save(effectFailTechnicalAction);
		effectFailTechnicalAction = effectFailTechnicalActionRepository.findOne(effectFailTechnicalAction.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",effectFailTechnicalAction.getId());
		return new EffectFailTechnicalActionDetailsEvent(new EffectFailTechnicalActionDetails().toEffectFailTechnicalActionDetails(effectFailTechnicalAction));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"effectFailTechnicalAction", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public EffectFailTechnicalActionPageEvent requestEffectFailTechnicalActionPage(RequestEffectFailTechnicalActionPageEvent event) throws Exception {
		Page<EffectFailTechnicalAction> effectFailTechnicalActions = effectFailTechnicalActionRepository.findAll(event.getPageable());
		return new EffectFailTechnicalActionPageEvent(effectFailTechnicalActions);
	}

	public EffectFailTechnicalActionDetailsEvent requestEffectFailTechnicalActionDetails(RequestEffectFailTechnicalActionDetailsEvent event) throws Exception {
		EffectFailTechnicalActionDetails effectFailTechnicalActionDetails=null;
		if (event.getId()!=null){
			EffectFailTechnicalAction effectFailTechnicalAction = effectFailTechnicalActionRepository.findOne(event.getId());
			if (effectFailTechnicalAction!=null)
				effectFailTechnicalActionDetails = new EffectFailTechnicalActionDetails().toEffectFailTechnicalActionDetails(effectFailTechnicalAction);
		}
		return new EffectFailTechnicalActionDetailsEvent(effectFailTechnicalActionDetails);
		
	}

	public EffectFailTechnicalActionDetailsEvent modifyEffectFailTechnicalAction(EffectFailTechnicalActionModificationEvent event) throws Exception {
		EffectFailTechnicalAction effectFailTechnicalAction = new EffectFailTechnicalAction().fromEffectFailTechnicalActionDetails(event.getEffectFailTechnicalActionDetails());
		effectFailTechnicalAction = effectFailTechnicalActionRepository.save(effectFailTechnicalAction);
		effectFailTechnicalAction = effectFailTechnicalActionRepository.findOne(effectFailTechnicalAction.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",effectFailTechnicalAction.getId());
		return new EffectFailTechnicalActionDetailsEvent(new EffectFailTechnicalActionDetails().toEffectFailTechnicalActionDetails(effectFailTechnicalAction));
	}
	
		public EffectFailTechnicalActionDetailsEvent getOnlyRecord() throws Exception {
			Iterable<EffectFailTechnicalAction> all = effectFailTechnicalActionRepository.findAll();
			EffectFailTechnicalActionDetails single = new EffectFailTechnicalActionDetails();
			for (EffectFailTechnicalAction effectFailTechnicalAction : all) {
				single=new EffectFailTechnicalActionDetails().toEffectFailTechnicalActionDetails(effectFailTechnicalAction);
				break;
			}
			return new EffectFailTechnicalActionDetailsEvent(single);
		}
	
	public EffectFailTechnicalActionPageEvent requestAllByEffectFail(EmbeddedField... fields) throws Exception {
			List<EffectFailTechnicalAction> all = effectFailTechnicalActionCustomRepo.findAllActiveByEffectFail(fields);
			List<EffectFailTechnicalActionDetails> list = new ArrayList<EffectFailTechnicalActionDetails>();
			for (EffectFailTechnicalAction effectFailTechnicalAction : all) {
				list.add(new EffectFailTechnicalActionDetails().toEffectFailTechnicalActionDetails(effectFailTechnicalAction, fields));
			}
			return new EffectFailTechnicalActionPageEvent(list);
		}public EffectFailTechnicalActionPageEvent requestAllByTechnicalAction(EmbeddedField... fields) throws Exception {
			List<EffectFailTechnicalAction> all = effectFailTechnicalActionCustomRepo.findAllActiveByTechnicalAction(fields);
			List<EffectFailTechnicalActionDetails> list = new ArrayList<EffectFailTechnicalActionDetails>();
			for (EffectFailTechnicalAction effectFailTechnicalAction : all) {
				list.add(new EffectFailTechnicalActionDetails().toEffectFailTechnicalActionDetails(effectFailTechnicalAction, fields));
			}
			return new EffectFailTechnicalActionPageEvent(list);
		}
	

	public EffectFailTechnicalActionPageEvent requestEffectFailTechnicalActionFilterPage(RequestEffectFailTechnicalActionPageEvent event) throws Exception {
		Page<EffectFailTechnicalAction> page = effectFailTechnicalActionCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new EffectFailTechnicalActionPageEvent(page, effectFailTechnicalActionCustomRepo.getTotalCount());
	}
	
	public EffectFailTechnicalActionPageEvent requestEffectFailTechnicalActionFilter(
			RequestEffectFailTechnicalActionPageEvent event) throws Exception {
		List<EffectFailTechnicalAction> all = effectFailTechnicalActionCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		EffectFailTechnicalActionPageEvent pageEvent = new EffectFailTechnicalActionPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}