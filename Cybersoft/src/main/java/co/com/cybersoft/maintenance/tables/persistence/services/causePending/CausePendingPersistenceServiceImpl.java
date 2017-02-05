package co.com.cybersoft.maintenance.tables.persistence.services.causePending;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.CausePendingDetails;
import co.com.cybersoft.maintenance.tables.events.causePending.CreateCausePendingEvent;
import co.com.cybersoft.maintenance.tables.events.causePending.CausePendingDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.causePending.CausePendingPageEvent;
import co.com.cybersoft.maintenance.tables.events.causePending.CausePendingModificationEvent;
import co.com.cybersoft.maintenance.tables.events.causePending.RequestCausePendingDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.causePending.RequestCausePendingPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.CausePending;
import co.com.cybersoft.maintenance.tables.persistence.repository.causePending.CausePendingRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.causePending.CausePendingCustomRepo;
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
public class CausePendingPersistenceServiceImpl implements CausePendingPersistenceService{

	private final CausePendingRepository causePendingRepository;
	
	private final CausePendingCustomRepo causePendingCustomRepo;
	
	
	public CausePendingPersistenceServiceImpl(final CausePendingRepository causePendingRepository, final CausePendingCustomRepo causePendingCustomRepo) {
		this.causePendingRepository=causePendingRepository;
		this.causePendingCustomRepo=causePendingCustomRepo;
	}
	
	public CausePendingDetailsEvent createCausePending(CreateCausePendingEvent event) throws Exception{
		CausePending causePending = new CausePending().fromCausePendingDetails(event.getCausePendingDetails());
		causePending = causePendingRepository.save(causePending);
		causePending = causePendingRepository.findOne(causePending.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",causePending.getId());
		return new CausePendingDetailsEvent(new CausePendingDetails().toCausePendingDetails(causePending));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"causePending", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public CausePendingPageEvent requestCausePendingPage(RequestCausePendingPageEvent event) throws Exception {
		Page<CausePending> causePendings = causePendingRepository.findAll(event.getPageable());
		return new CausePendingPageEvent(causePendings);
	}

	public CausePendingDetailsEvent requestCausePendingDetails(RequestCausePendingDetailsEvent event) throws Exception {
		CausePendingDetails causePendingDetails=null;
		if (event.getId()!=null){
			CausePending causePending = causePendingRepository.findOne(event.getId());
			if (causePending!=null)
				causePendingDetails = new CausePendingDetails().toCausePendingDetails(causePending);
		}
		return new CausePendingDetailsEvent(causePendingDetails);
		
	}

	public CausePendingDetailsEvent modifyCausePending(CausePendingModificationEvent event) throws Exception {
		CausePending causePending = new CausePending().fromCausePendingDetails(event.getCausePendingDetails());
		causePending = causePendingRepository.save(causePending);
		causePending = causePendingRepository.findOne(causePending.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",causePending.getId());
		return new CausePendingDetailsEvent(new CausePendingDetails().toCausePendingDetails(causePending));
	}
	
		public CausePendingDetailsEvent getOnlyRecord() throws Exception {
			Iterable<CausePending> all = causePendingRepository.findAll();
			CausePendingDetails single = new CausePendingDetails();
			for (CausePending causePending : all) {
				single=new CausePendingDetails().toCausePendingDetails(causePending);
				break;
			}
			return new CausePendingDetailsEvent(single);
		}
	
	public CausePendingPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<CausePending> all = causePendingCustomRepo.findAllActiveByCompany(fields);
			List<CausePendingDetails> list = new ArrayList<CausePendingDetails>();
			for (CausePending causePending : all) {
				list.add(new CausePendingDetails().toCausePendingDetails(causePending, fields));
			}
			return new CausePendingPageEvent(list);
		}public CausePendingPageEvent requestAllByNameCausePending(EmbeddedField... fields) throws Exception {
			List<CausePending> all = causePendingCustomRepo.findAllActiveByNameCausePending(fields);
			List<CausePendingDetails> list = new ArrayList<CausePendingDetails>();
			for (CausePending causePending : all) {
				list.add(new CausePendingDetails().toCausePendingDetails(causePending, fields));
			}
			return new CausePendingPageEvent(list);
		}
	

	public CausePendingPageEvent requestCausePendingFilterPage(RequestCausePendingPageEvent event) throws Exception {
		Page<CausePending> page = causePendingCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new CausePendingPageEvent(page, causePendingCustomRepo.getTotalCount());
	}
	
	public CausePendingPageEvent requestCausePendingFilter(
			RequestCausePendingPageEvent event) throws Exception {
		List<CausePending> all = causePendingCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		CausePendingPageEvent pageEvent = new CausePendingPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}