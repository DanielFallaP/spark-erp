package co.com.cybersoft.maintenance.tables.persistence.services.failureCause;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.FailureCauseDetails;
import co.com.cybersoft.maintenance.tables.events.failureCause.CreateFailureCauseEvent;
import co.com.cybersoft.maintenance.tables.events.failureCause.FailureCauseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.failureCause.FailureCausePageEvent;
import co.com.cybersoft.maintenance.tables.events.failureCause.FailureCauseModificationEvent;
import co.com.cybersoft.maintenance.tables.events.failureCause.RequestFailureCauseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.failureCause.RequestFailureCausePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.FailureCause;
import co.com.cybersoft.maintenance.tables.persistence.repository.failureCause.FailureCauseRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.failureCause.FailureCauseCustomRepo;
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
public class FailureCausePersistenceServiceImpl implements FailureCausePersistenceService{

	private final FailureCauseRepository failureCauseRepository;
	
	private final FailureCauseCustomRepo failureCauseCustomRepo;
	
	
	public FailureCausePersistenceServiceImpl(final FailureCauseRepository failureCauseRepository, final FailureCauseCustomRepo failureCauseCustomRepo) {
		this.failureCauseRepository=failureCauseRepository;
		this.failureCauseCustomRepo=failureCauseCustomRepo;
	}
	
	public FailureCauseDetailsEvent createFailureCause(CreateFailureCauseEvent event) throws Exception{
		FailureCause failureCause = new FailureCause().fromFailureCauseDetails(event.getFailureCauseDetails());
		failureCause = failureCauseRepository.save(failureCause);
		failureCause = failureCauseRepository.findOne(failureCause.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",failureCause.getId());
		return new FailureCauseDetailsEvent(new FailureCauseDetails().toFailureCauseDetails(failureCause));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"failureCause", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public FailureCausePageEvent requestFailureCausePage(RequestFailureCausePageEvent event) throws Exception {
		Page<FailureCause> failureCauses = failureCauseRepository.findAll(event.getPageable());
		return new FailureCausePageEvent(failureCauses);
	}

	public FailureCauseDetailsEvent requestFailureCauseDetails(RequestFailureCauseDetailsEvent event) throws Exception {
		FailureCauseDetails failureCauseDetails=null;
		if (event.getId()!=null){
			FailureCause failureCause = failureCauseRepository.findOne(event.getId());
			if (failureCause!=null)
				failureCauseDetails = new FailureCauseDetails().toFailureCauseDetails(failureCause);
		}
		return new FailureCauseDetailsEvent(failureCauseDetails);
		
	}

	public FailureCauseDetailsEvent modifyFailureCause(FailureCauseModificationEvent event) throws Exception {
		FailureCause failureCause = new FailureCause().fromFailureCauseDetails(event.getFailureCauseDetails());
		failureCause = failureCauseRepository.save(failureCause);
		failureCause = failureCauseRepository.findOne(failureCause.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",failureCause.getId());
		return new FailureCauseDetailsEvent(new FailureCauseDetails().toFailureCauseDetails(failureCause));
	}
	
		public FailureCauseDetailsEvent getOnlyRecord() throws Exception {
			Iterable<FailureCause> all = failureCauseRepository.findAll();
			FailureCauseDetails single = new FailureCauseDetails();
			for (FailureCause failureCause : all) {
				single=new FailureCauseDetails().toFailureCauseDetails(failureCause);
				break;
			}
			return new FailureCauseDetailsEvent(single);
		}
	
	public FailureCausePageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<FailureCause> all = failureCauseCustomRepo.findAllActiveByCompany(fields);
			List<FailureCauseDetails> list = new ArrayList<FailureCauseDetails>();
			for (FailureCause failureCause : all) {
				list.add(new FailureCauseDetails().toFailureCauseDetails(failureCause, fields));
			}
			return new FailureCausePageEvent(list);
		}public FailureCausePageEvent requestAllByNameFailureCause(EmbeddedField... fields) throws Exception {
			List<FailureCause> all = failureCauseCustomRepo.findAllActiveByNameFailureCause(fields);
			List<FailureCauseDetails> list = new ArrayList<FailureCauseDetails>();
			for (FailureCause failureCause : all) {
				list.add(new FailureCauseDetails().toFailureCauseDetails(failureCause, fields));
			}
			return new FailureCausePageEvent(list);
		}
	

	public FailureCausePageEvent requestFailureCauseFilterPage(RequestFailureCausePageEvent event) throws Exception {
		Page<FailureCause> page = failureCauseCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new FailureCausePageEvent(page, failureCauseCustomRepo.getTotalCount());
	}
	
	public FailureCausePageEvent requestFailureCauseFilter(
			RequestFailureCausePageEvent event) throws Exception {
		List<FailureCause> all = failureCauseCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		FailureCausePageEvent pageEvent = new FailureCausePageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}