package co.com.cybersoft.maintenance.tables.persistence.services.failureCauseTechnicalaction;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.FailureCauseTechnicalactionDetails;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.CreateFailureCauseTechnicalactionEvent;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.FailureCauseTechnicalactionDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.FailureCauseTechnicalactionPageEvent;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.FailureCauseTechnicalactionModificationEvent;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.RequestFailureCauseTechnicalactionDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.RequestFailureCauseTechnicalactionPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.FailureCauseTechnicalaction;
import co.com.cybersoft.maintenance.tables.persistence.repository.failureCauseTechnicalaction.FailureCauseTechnicalactionRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.failureCauseTechnicalaction.FailureCauseTechnicalactionCustomRepo;
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
public class FailureCauseTechnicalactionPersistenceServiceImpl implements FailureCauseTechnicalactionPersistenceService{

	private final FailureCauseTechnicalactionRepository failureCauseTechnicalactionRepository;
	
	private final FailureCauseTechnicalactionCustomRepo failureCauseTechnicalactionCustomRepo;
	
	
	public FailureCauseTechnicalactionPersistenceServiceImpl(final FailureCauseTechnicalactionRepository failureCauseTechnicalactionRepository, final FailureCauseTechnicalactionCustomRepo failureCauseTechnicalactionCustomRepo) {
		this.failureCauseTechnicalactionRepository=failureCauseTechnicalactionRepository;
		this.failureCauseTechnicalactionCustomRepo=failureCauseTechnicalactionCustomRepo;
	}
	
	public FailureCauseTechnicalactionDetailsEvent createFailureCauseTechnicalaction(CreateFailureCauseTechnicalactionEvent event) throws Exception{
		FailureCauseTechnicalaction failureCauseTechnicalaction = new FailureCauseTechnicalaction().fromFailureCauseTechnicalactionDetails(event.getFailureCauseTechnicalactionDetails());
		failureCauseTechnicalaction = failureCauseTechnicalactionRepository.save(failureCauseTechnicalaction);
		failureCauseTechnicalaction = failureCauseTechnicalactionRepository.findOne(failureCauseTechnicalaction.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",failureCauseTechnicalaction.getId());
		return new FailureCauseTechnicalactionDetailsEvent(new FailureCauseTechnicalactionDetails().toFailureCauseTechnicalactionDetails(failureCauseTechnicalaction));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"failureCauseTechnicalaction", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public FailureCauseTechnicalactionPageEvent requestFailureCauseTechnicalactionPage(RequestFailureCauseTechnicalactionPageEvent event) throws Exception {
		Page<FailureCauseTechnicalaction> failureCauseTechnicalactions = failureCauseTechnicalactionRepository.findAll(event.getPageable());
		return new FailureCauseTechnicalactionPageEvent(failureCauseTechnicalactions);
	}

	public FailureCauseTechnicalactionDetailsEvent requestFailureCauseTechnicalactionDetails(RequestFailureCauseTechnicalactionDetailsEvent event) throws Exception {
		FailureCauseTechnicalactionDetails failureCauseTechnicalactionDetails=null;
		if (event.getId()!=null){
			FailureCauseTechnicalaction failureCauseTechnicalaction = failureCauseTechnicalactionRepository.findOne(event.getId());
			if (failureCauseTechnicalaction!=null)
				failureCauseTechnicalactionDetails = new FailureCauseTechnicalactionDetails().toFailureCauseTechnicalactionDetails(failureCauseTechnicalaction);
		}
		return new FailureCauseTechnicalactionDetailsEvent(failureCauseTechnicalactionDetails);
		
	}

	public FailureCauseTechnicalactionDetailsEvent modifyFailureCauseTechnicalaction(FailureCauseTechnicalactionModificationEvent event) throws Exception {
		FailureCauseTechnicalaction failureCauseTechnicalaction = new FailureCauseTechnicalaction().fromFailureCauseTechnicalactionDetails(event.getFailureCauseTechnicalactionDetails());
		failureCauseTechnicalaction = failureCauseTechnicalactionRepository.save(failureCauseTechnicalaction);
		failureCauseTechnicalaction = failureCauseTechnicalactionRepository.findOne(failureCauseTechnicalaction.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",failureCauseTechnicalaction.getId());
		return new FailureCauseTechnicalactionDetailsEvent(new FailureCauseTechnicalactionDetails().toFailureCauseTechnicalactionDetails(failureCauseTechnicalaction));
	}
	
		public FailureCauseTechnicalactionDetailsEvent getOnlyRecord() throws Exception {
			Iterable<FailureCauseTechnicalaction> all = failureCauseTechnicalactionRepository.findAll();
			FailureCauseTechnicalactionDetails single = new FailureCauseTechnicalactionDetails();
			for (FailureCauseTechnicalaction failureCauseTechnicalaction : all) {
				single=new FailureCauseTechnicalactionDetails().toFailureCauseTechnicalactionDetails(failureCauseTechnicalaction);
				break;
			}
			return new FailureCauseTechnicalactionDetailsEvent(single);
		}
	
	public FailureCauseTechnicalactionPageEvent requestAllByFailureCause(EmbeddedField... fields) throws Exception {
			List<FailureCauseTechnicalaction> all = failureCauseTechnicalactionCustomRepo.findAllActiveByFailureCause(fields);
			List<FailureCauseTechnicalactionDetails> list = new ArrayList<FailureCauseTechnicalactionDetails>();
			for (FailureCauseTechnicalaction failureCauseTechnicalaction : all) {
				list.add(new FailureCauseTechnicalactionDetails().toFailureCauseTechnicalactionDetails(failureCauseTechnicalaction, fields));
			}
			return new FailureCauseTechnicalactionPageEvent(list);
		}public FailureCauseTechnicalactionPageEvent requestAllByTechnicalAction(EmbeddedField... fields) throws Exception {
			List<FailureCauseTechnicalaction> all = failureCauseTechnicalactionCustomRepo.findAllActiveByTechnicalAction(fields);
			List<FailureCauseTechnicalactionDetails> list = new ArrayList<FailureCauseTechnicalactionDetails>();
			for (FailureCauseTechnicalaction failureCauseTechnicalaction : all) {
				list.add(new FailureCauseTechnicalactionDetails().toFailureCauseTechnicalactionDetails(failureCauseTechnicalaction, fields));
			}
			return new FailureCauseTechnicalactionPageEvent(list);
		}
	

	public FailureCauseTechnicalactionPageEvent requestFailureCauseTechnicalactionFilterPage(RequestFailureCauseTechnicalactionPageEvent event) throws Exception {
		Page<FailureCauseTechnicalaction> page = failureCauseTechnicalactionCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new FailureCauseTechnicalactionPageEvent(page, failureCauseTechnicalactionCustomRepo.getTotalCount());
	}
	
	public FailureCauseTechnicalactionPageEvent requestFailureCauseTechnicalactionFilter(
			RequestFailureCauseTechnicalactionPageEvent event) throws Exception {
		List<FailureCauseTechnicalaction> all = failureCauseTechnicalactionCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		FailureCauseTechnicalactionPageEvent pageEvent = new FailureCauseTechnicalactionPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}