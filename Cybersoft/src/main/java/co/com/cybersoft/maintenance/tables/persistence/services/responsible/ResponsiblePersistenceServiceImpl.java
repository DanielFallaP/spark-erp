package co.com.cybersoft.maintenance.tables.persistence.services.responsible;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.ResponsibleDetails;
import co.com.cybersoft.maintenance.tables.events.responsible.CreateResponsibleEvent;
import co.com.cybersoft.maintenance.tables.events.responsible.ResponsibleDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.responsible.ResponsiblePageEvent;
import co.com.cybersoft.maintenance.tables.events.responsible.ResponsibleModificationEvent;
import co.com.cybersoft.maintenance.tables.events.responsible.RequestResponsibleDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.responsible.RequestResponsiblePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Responsible;
import co.com.cybersoft.maintenance.tables.persistence.repository.responsible.ResponsibleRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.responsible.ResponsibleCustomRepo;
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
public class ResponsiblePersistenceServiceImpl implements ResponsiblePersistenceService{

	private final ResponsibleRepository responsibleRepository;
	
	private final ResponsibleCustomRepo responsibleCustomRepo;
	
	
	public ResponsiblePersistenceServiceImpl(final ResponsibleRepository responsibleRepository, final ResponsibleCustomRepo responsibleCustomRepo) {
		this.responsibleRepository=responsibleRepository;
		this.responsibleCustomRepo=responsibleCustomRepo;
	}
	
	public ResponsibleDetailsEvent createResponsible(CreateResponsibleEvent event) throws Exception{
		Responsible responsible = new Responsible().fromResponsibleDetails(event.getResponsibleDetails());
		responsible = responsibleRepository.save(responsible);
		responsible = responsibleRepository.findOne(responsible.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",responsible.getId());
		return new ResponsibleDetailsEvent(new ResponsibleDetails().toResponsibleDetails(responsible));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"responsible", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public ResponsiblePageEvent requestResponsiblePage(RequestResponsiblePageEvent event) throws Exception {
		Page<Responsible> responsibles = responsibleRepository.findAll(event.getPageable());
		return new ResponsiblePageEvent(responsibles);
	}

	public ResponsibleDetailsEvent requestResponsibleDetails(RequestResponsibleDetailsEvent event) throws Exception {
		ResponsibleDetails responsibleDetails=null;
		if (event.getId()!=null){
			Responsible responsible = responsibleRepository.findOne(event.getId());
			if (responsible!=null)
				responsibleDetails = new ResponsibleDetails().toResponsibleDetails(responsible);
		}
		return new ResponsibleDetailsEvent(responsibleDetails);
		
	}

	public ResponsibleDetailsEvent modifyResponsible(ResponsibleModificationEvent event) throws Exception {
		Responsible responsible = new Responsible().fromResponsibleDetails(event.getResponsibleDetails());
		responsible = responsibleRepository.save(responsible);
		responsible = responsibleRepository.findOne(responsible.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",responsible.getId());
		return new ResponsibleDetailsEvent(new ResponsibleDetails().toResponsibleDetails(responsible));
	}
	
		public ResponsibleDetailsEvent getOnlyRecord() throws Exception {
			Iterable<Responsible> all = responsibleRepository.findAll();
			ResponsibleDetails single = new ResponsibleDetails();
			for (Responsible responsible : all) {
				single=new ResponsibleDetails().toResponsibleDetails(responsible);
				break;
			}
			return new ResponsibleDetailsEvent(single);
		}
	
	public ResponsiblePageEvent requestAllByJob(EmbeddedField... fields) throws Exception {
			List<Responsible> all = responsibleCustomRepo.findAllActiveByJob(fields);
			List<ResponsibleDetails> list = new ArrayList<ResponsibleDetails>();
			for (Responsible responsible : all) {
				list.add(new ResponsibleDetails().toResponsibleDetails(responsible, fields));
			}
			return new ResponsiblePageEvent(list);
		}public ResponsiblePageEvent requestAllByThird(EmbeddedField... fields) throws Exception {
			List<Responsible> all = responsibleCustomRepo.findAllActiveByThird(fields);
			List<ResponsibleDetails> list = new ArrayList<ResponsibleDetails>();
			for (Responsible responsible : all) {
				list.add(new ResponsibleDetails().toResponsibleDetails(responsible, fields));
			}
			return new ResponsiblePageEvent(list);
		}
	

	public ResponsiblePageEvent requestResponsibleFilterPage(RequestResponsiblePageEvent event) throws Exception {
		Page<Responsible> page = responsibleCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new ResponsiblePageEvent(page, responsibleCustomRepo.getTotalCount());
	}
	
	public ResponsiblePageEvent requestResponsibleFilter(
			RequestResponsiblePageEvent event) throws Exception {
		List<Responsible> all = responsibleCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		ResponsiblePageEvent pageEvent = new ResponsiblePageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}