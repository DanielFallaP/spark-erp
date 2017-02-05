package co.com.cybersoft.maintenance.tables.persistence.services.responsibleCenter;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.ResponsibleCenterDetails;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.CreateResponsibleCenterEvent;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.ResponsibleCenterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.ResponsibleCenterPageEvent;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.ResponsibleCenterModificationEvent;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.RequestResponsibleCenterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.RequestResponsibleCenterPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.ResponsibleCenter;
import co.com.cybersoft.maintenance.tables.persistence.repository.responsibleCenter.ResponsibleCenterRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.responsibleCenter.ResponsibleCenterCustomRepo;
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
public class ResponsibleCenterPersistenceServiceImpl implements ResponsibleCenterPersistenceService{

	private final ResponsibleCenterRepository responsibleCenterRepository;
	
	private final ResponsibleCenterCustomRepo responsibleCenterCustomRepo;
	
	
	public ResponsibleCenterPersistenceServiceImpl(final ResponsibleCenterRepository responsibleCenterRepository, final ResponsibleCenterCustomRepo responsibleCenterCustomRepo) {
		this.responsibleCenterRepository=responsibleCenterRepository;
		this.responsibleCenterCustomRepo=responsibleCenterCustomRepo;
	}
	
	public ResponsibleCenterDetailsEvent createResponsibleCenter(CreateResponsibleCenterEvent event) throws Exception{
		ResponsibleCenter responsibleCenter = new ResponsibleCenter().fromResponsibleCenterDetails(event.getResponsibleCenterDetails());
		responsibleCenter = responsibleCenterRepository.save(responsibleCenter);
		responsibleCenter = responsibleCenterRepository.findOne(responsibleCenter.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",responsibleCenter.getId());
		return new ResponsibleCenterDetailsEvent(new ResponsibleCenterDetails().toResponsibleCenterDetails(responsibleCenter));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"responsibleCenter", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public ResponsibleCenterPageEvent requestResponsibleCenterPage(RequestResponsibleCenterPageEvent event) throws Exception {
		Page<ResponsibleCenter> responsibleCenters = responsibleCenterRepository.findAll(event.getPageable());
		return new ResponsibleCenterPageEvent(responsibleCenters);
	}

	public ResponsibleCenterDetailsEvent requestResponsibleCenterDetails(RequestResponsibleCenterDetailsEvent event) throws Exception {
		ResponsibleCenterDetails responsibleCenterDetails=null;
		if (event.getId()!=null){
			ResponsibleCenter responsibleCenter = responsibleCenterRepository.findOne(event.getId());
			if (responsibleCenter!=null)
				responsibleCenterDetails = new ResponsibleCenterDetails().toResponsibleCenterDetails(responsibleCenter);
		}
		return new ResponsibleCenterDetailsEvent(responsibleCenterDetails);
		
	}

	public ResponsibleCenterDetailsEvent modifyResponsibleCenter(ResponsibleCenterModificationEvent event) throws Exception {
		ResponsibleCenter responsibleCenter = new ResponsibleCenter().fromResponsibleCenterDetails(event.getResponsibleCenterDetails());
		responsibleCenter = responsibleCenterRepository.save(responsibleCenter);
		responsibleCenter = responsibleCenterRepository.findOne(responsibleCenter.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",responsibleCenter.getId());
		return new ResponsibleCenterDetailsEvent(new ResponsibleCenterDetails().toResponsibleCenterDetails(responsibleCenter));
	}
	
		public ResponsibleCenterDetailsEvent getOnlyRecord() throws Exception {
			Iterable<ResponsibleCenter> all = responsibleCenterRepository.findAll();
			ResponsibleCenterDetails single = new ResponsibleCenterDetails();
			for (ResponsibleCenter responsibleCenter : all) {
				single=new ResponsibleCenterDetails().toResponsibleCenterDetails(responsibleCenter);
				break;
			}
			return new ResponsibleCenterDetailsEvent(single);
		}
	
	public ResponsibleCenterPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<ResponsibleCenter> all = responsibleCenterCustomRepo.findAllActiveByCompany(fields);
			List<ResponsibleCenterDetails> list = new ArrayList<ResponsibleCenterDetails>();
			for (ResponsibleCenter responsibleCenter : all) {
				list.add(new ResponsibleCenterDetails().toResponsibleCenterDetails(responsibleCenter, fields));
			}
			return new ResponsibleCenterPageEvent(list);
		}public ResponsibleCenterPageEvent requestAllByNameResponsibleCenter(EmbeddedField... fields) throws Exception {
			List<ResponsibleCenter> all = responsibleCenterCustomRepo.findAllActiveByNameResponsibleCenter(fields);
			List<ResponsibleCenterDetails> list = new ArrayList<ResponsibleCenterDetails>();
			for (ResponsibleCenter responsibleCenter : all) {
				list.add(new ResponsibleCenterDetails().toResponsibleCenterDetails(responsibleCenter, fields));
			}
			return new ResponsibleCenterPageEvent(list);
		}
	

	public ResponsibleCenterPageEvent requestResponsibleCenterFilterPage(RequestResponsibleCenterPageEvent event) throws Exception {
		Page<ResponsibleCenter> page = responsibleCenterCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new ResponsibleCenterPageEvent(page, responsibleCenterCustomRepo.getTotalCount());
	}
	
	public ResponsibleCenterPageEvent requestResponsibleCenterFilter(
			RequestResponsibleCenterPageEvent event) throws Exception {
		List<ResponsibleCenter> all = responsibleCenterCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		ResponsibleCenterPageEvent pageEvent = new ResponsibleCenterPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}