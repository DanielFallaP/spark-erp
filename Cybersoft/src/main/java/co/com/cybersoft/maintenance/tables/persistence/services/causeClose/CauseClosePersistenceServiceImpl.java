package co.com.cybersoft.maintenance.tables.persistence.services.causeClose;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.CauseCloseDetails;
import co.com.cybersoft.maintenance.tables.events.causeClose.CreateCauseCloseEvent;
import co.com.cybersoft.maintenance.tables.events.causeClose.CauseCloseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.causeClose.CauseClosePageEvent;
import co.com.cybersoft.maintenance.tables.events.causeClose.CauseCloseModificationEvent;
import co.com.cybersoft.maintenance.tables.events.causeClose.RequestCauseCloseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.causeClose.RequestCauseClosePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.CauseClose;
import co.com.cybersoft.maintenance.tables.persistence.repository.causeClose.CauseCloseRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.causeClose.CauseCloseCustomRepo;
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
public class CauseClosePersistenceServiceImpl implements CauseClosePersistenceService{

	private final CauseCloseRepository causeCloseRepository;
	
	private final CauseCloseCustomRepo causeCloseCustomRepo;
	
	
	public CauseClosePersistenceServiceImpl(final CauseCloseRepository causeCloseRepository, final CauseCloseCustomRepo causeCloseCustomRepo) {
		this.causeCloseRepository=causeCloseRepository;
		this.causeCloseCustomRepo=causeCloseCustomRepo;
	}
	
	public CauseCloseDetailsEvent createCauseClose(CreateCauseCloseEvent event) throws Exception{
		CauseClose causeClose = new CauseClose().fromCauseCloseDetails(event.getCauseCloseDetails());
		causeClose = causeCloseRepository.save(causeClose);
		causeClose = causeCloseRepository.findOne(causeClose.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",causeClose.getId());
		return new CauseCloseDetailsEvent(new CauseCloseDetails().toCauseCloseDetails(causeClose));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"causeClose", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public CauseClosePageEvent requestCauseClosePage(RequestCauseClosePageEvent event) throws Exception {
		Page<CauseClose> causeCloses = causeCloseRepository.findAll(event.getPageable());
		return new CauseClosePageEvent(causeCloses);
	}

	public CauseCloseDetailsEvent requestCauseCloseDetails(RequestCauseCloseDetailsEvent event) throws Exception {
		CauseCloseDetails causeCloseDetails=null;
		if (event.getId()!=null){
			CauseClose causeClose = causeCloseRepository.findOne(event.getId());
			if (causeClose!=null)
				causeCloseDetails = new CauseCloseDetails().toCauseCloseDetails(causeClose);
		}
		return new CauseCloseDetailsEvent(causeCloseDetails);
		
	}

	public CauseCloseDetailsEvent modifyCauseClose(CauseCloseModificationEvent event) throws Exception {
		CauseClose causeClose = new CauseClose().fromCauseCloseDetails(event.getCauseCloseDetails());
		causeClose = causeCloseRepository.save(causeClose);
		causeClose = causeCloseRepository.findOne(causeClose.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",causeClose.getId());
		return new CauseCloseDetailsEvent(new CauseCloseDetails().toCauseCloseDetails(causeClose));
	}
	
		public CauseCloseDetailsEvent getOnlyRecord() throws Exception {
			Iterable<CauseClose> all = causeCloseRepository.findAll();
			CauseCloseDetails single = new CauseCloseDetails();
			for (CauseClose causeClose : all) {
				single=new CauseCloseDetails().toCauseCloseDetails(causeClose);
				break;
			}
			return new CauseCloseDetailsEvent(single);
		}
	
	public CauseClosePageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<CauseClose> all = causeCloseCustomRepo.findAllActiveByCompany(fields);
			List<CauseCloseDetails> list = new ArrayList<CauseCloseDetails>();
			for (CauseClose causeClose : all) {
				list.add(new CauseCloseDetails().toCauseCloseDetails(causeClose, fields));
			}
			return new CauseClosePageEvent(list);
		}public CauseClosePageEvent requestAllByNameCauseClose(EmbeddedField... fields) throws Exception {
			List<CauseClose> all = causeCloseCustomRepo.findAllActiveByNameCauseClose(fields);
			List<CauseCloseDetails> list = new ArrayList<CauseCloseDetails>();
			for (CauseClose causeClose : all) {
				list.add(new CauseCloseDetails().toCauseCloseDetails(causeClose, fields));
			}
			return new CauseClosePageEvent(list);
		}public CauseClosePageEvent requestAllByTypeCauseClose(EmbeddedField... fields) throws Exception {
			List<CauseClose> all = causeCloseCustomRepo.findAllActiveByTypeCauseClose(fields);
			List<CauseCloseDetails> list = new ArrayList<CauseCloseDetails>();
			for (CauseClose causeClose : all) {
				list.add(new CauseCloseDetails().toCauseCloseDetails(causeClose, fields));
			}
			return new CauseClosePageEvent(list);
		}
	

	public CauseClosePageEvent requestCauseCloseFilterPage(RequestCauseClosePageEvent event) throws Exception {
		Page<CauseClose> page = causeCloseCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new CauseClosePageEvent(page, causeCloseCustomRepo.getTotalCount());
	}
	
	public CauseClosePageEvent requestCauseCloseFilter(
			RequestCauseClosePageEvent event) throws Exception {
		List<CauseClose> all = causeCloseCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		CauseClosePageEvent pageEvent = new CauseClosePageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}