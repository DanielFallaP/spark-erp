package co.com.cybersoft.maintenance.tables.persistence.services.technicalAction;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.TechnicalActionDetails;
import co.com.cybersoft.maintenance.tables.events.technicalAction.CreateTechnicalActionEvent;
import co.com.cybersoft.maintenance.tables.events.technicalAction.TechnicalActionDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.technicalAction.TechnicalActionPageEvent;
import co.com.cybersoft.maintenance.tables.events.technicalAction.TechnicalActionModificationEvent;
import co.com.cybersoft.maintenance.tables.events.technicalAction.RequestTechnicalActionDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.technicalAction.RequestTechnicalActionPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.TechnicalAction;
import co.com.cybersoft.maintenance.tables.persistence.repository.technicalAction.TechnicalActionRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.technicalAction.TechnicalActionCustomRepo;
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
public class TechnicalActionPersistenceServiceImpl implements TechnicalActionPersistenceService{

	private final TechnicalActionRepository technicalActionRepository;
	
	private final TechnicalActionCustomRepo technicalActionCustomRepo;
	
	
	public TechnicalActionPersistenceServiceImpl(final TechnicalActionRepository technicalActionRepository, final TechnicalActionCustomRepo technicalActionCustomRepo) {
		this.technicalActionRepository=technicalActionRepository;
		this.technicalActionCustomRepo=technicalActionCustomRepo;
	}
	
	public TechnicalActionDetailsEvent createTechnicalAction(CreateTechnicalActionEvent event) throws Exception{
		TechnicalAction technicalAction = new TechnicalAction().fromTechnicalActionDetails(event.getTechnicalActionDetails());
		technicalAction = technicalActionRepository.save(technicalAction);
		technicalAction = technicalActionRepository.findOne(technicalAction.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",technicalAction.getId());
		return new TechnicalActionDetailsEvent(new TechnicalActionDetails().toTechnicalActionDetails(technicalAction));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"technicalAction", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public TechnicalActionPageEvent requestTechnicalActionPage(RequestTechnicalActionPageEvent event) throws Exception {
		Page<TechnicalAction> technicalActions = technicalActionRepository.findAll(event.getPageable());
		return new TechnicalActionPageEvent(technicalActions);
	}

	public TechnicalActionDetailsEvent requestTechnicalActionDetails(RequestTechnicalActionDetailsEvent event) throws Exception {
		TechnicalActionDetails technicalActionDetails=null;
		if (event.getId()!=null){
			TechnicalAction technicalAction = technicalActionRepository.findOne(event.getId());
			if (technicalAction!=null)
				technicalActionDetails = new TechnicalActionDetails().toTechnicalActionDetails(technicalAction);
		}
		return new TechnicalActionDetailsEvent(technicalActionDetails);
		
	}

	public TechnicalActionDetailsEvent modifyTechnicalAction(TechnicalActionModificationEvent event) throws Exception {
		TechnicalAction technicalAction = new TechnicalAction().fromTechnicalActionDetails(event.getTechnicalActionDetails());
		technicalAction = technicalActionRepository.save(technicalAction);
		technicalAction = technicalActionRepository.findOne(technicalAction.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",technicalAction.getId());
		return new TechnicalActionDetailsEvent(new TechnicalActionDetails().toTechnicalActionDetails(technicalAction));
	}
	
		public TechnicalActionDetailsEvent getOnlyRecord() throws Exception {
			Iterable<TechnicalAction> all = technicalActionRepository.findAll();
			TechnicalActionDetails single = new TechnicalActionDetails();
			for (TechnicalAction technicalAction : all) {
				single=new TechnicalActionDetails().toTechnicalActionDetails(technicalAction);
				break;
			}
			return new TechnicalActionDetailsEvent(single);
		}
	
	public TechnicalActionPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<TechnicalAction> all = technicalActionCustomRepo.findAllActiveByCompany(fields);
			List<TechnicalActionDetails> list = new ArrayList<TechnicalActionDetails>();
			for (TechnicalAction technicalAction : all) {
				list.add(new TechnicalActionDetails().toTechnicalActionDetails(technicalAction, fields));
			}
			return new TechnicalActionPageEvent(list);
		}public TechnicalActionPageEvent requestAllByCode(EmbeddedField... fields) throws Exception {
			List<TechnicalAction> all = technicalActionCustomRepo.findAllActiveByCode(fields);
			List<TechnicalActionDetails> list = new ArrayList<TechnicalActionDetails>();
			for (TechnicalAction technicalAction : all) {
				list.add(new TechnicalActionDetails().toTechnicalActionDetails(technicalAction, fields));
			}
			return new TechnicalActionPageEvent(list);
		}public TechnicalActionPageEvent requestAllByTechnicalActionName(EmbeddedField... fields) throws Exception {
			List<TechnicalAction> all = technicalActionCustomRepo.findAllActiveByTechnicalActionName(fields);
			List<TechnicalActionDetails> list = new ArrayList<TechnicalActionDetails>();
			for (TechnicalAction technicalAction : all) {
				list.add(new TechnicalActionDetails().toTechnicalActionDetails(technicalAction, fields));
			}
			return new TechnicalActionPageEvent(list);
		}
	

	public TechnicalActionPageEvent requestTechnicalActionFilterPage(RequestTechnicalActionPageEvent event) throws Exception {
		Page<TechnicalAction> page = technicalActionCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new TechnicalActionPageEvent(page, technicalActionCustomRepo.getTotalCount());
	}
	
	public TechnicalActionPageEvent requestTechnicalActionFilter(
			RequestTechnicalActionPageEvent event) throws Exception {
		List<TechnicalAction> all = technicalActionCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		TechnicalActionPageEvent pageEvent = new TechnicalActionPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}