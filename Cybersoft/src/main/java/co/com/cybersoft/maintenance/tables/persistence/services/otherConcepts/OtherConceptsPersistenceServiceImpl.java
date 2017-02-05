package co.com.cybersoft.maintenance.tables.persistence.services.otherConcepts;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.OtherConceptsDetails;
import co.com.cybersoft.maintenance.tables.events.otherConcepts.CreateOtherConceptsEvent;
import co.com.cybersoft.maintenance.tables.events.otherConcepts.OtherConceptsDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.otherConcepts.OtherConceptsPageEvent;
import co.com.cybersoft.maintenance.tables.events.otherConcepts.OtherConceptsModificationEvent;
import co.com.cybersoft.maintenance.tables.events.otherConcepts.RequestOtherConceptsDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.otherConcepts.RequestOtherConceptsPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.OtherConcepts;
import co.com.cybersoft.maintenance.tables.persistence.repository.otherConcepts.OtherConceptsRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.otherConcepts.OtherConceptsCustomRepo;
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
public class OtherConceptsPersistenceServiceImpl implements OtherConceptsPersistenceService{

	private final OtherConceptsRepository otherConceptsRepository;
	
	private final OtherConceptsCustomRepo otherConceptsCustomRepo;
	
	
	public OtherConceptsPersistenceServiceImpl(final OtherConceptsRepository otherConceptsRepository, final OtherConceptsCustomRepo otherConceptsCustomRepo) {
		this.otherConceptsRepository=otherConceptsRepository;
		this.otherConceptsCustomRepo=otherConceptsCustomRepo;
	}
	
	public OtherConceptsDetailsEvent createOtherConcepts(CreateOtherConceptsEvent event) throws Exception{
		OtherConcepts otherConcepts = new OtherConcepts().fromOtherConceptsDetails(event.getOtherConceptsDetails());
		otherConcepts = otherConceptsRepository.save(otherConcepts);
		otherConcepts = otherConceptsRepository.findOne(otherConcepts.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",otherConcepts.getId());
		return new OtherConceptsDetailsEvent(new OtherConceptsDetails().toOtherConceptsDetails(otherConcepts));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"otherConcepts", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public OtherConceptsPageEvent requestOtherConceptsPage(RequestOtherConceptsPageEvent event) throws Exception {
		Page<OtherConcepts> otherConceptss = otherConceptsRepository.findAll(event.getPageable());
		return new OtherConceptsPageEvent(otherConceptss);
	}

	public OtherConceptsDetailsEvent requestOtherConceptsDetails(RequestOtherConceptsDetailsEvent event) throws Exception {
		OtherConceptsDetails otherConceptsDetails=null;
		if (event.getId()!=null){
			OtherConcepts otherConcepts = otherConceptsRepository.findOne(event.getId());
			if (otherConcepts!=null)
				otherConceptsDetails = new OtherConceptsDetails().toOtherConceptsDetails(otherConcepts);
		}
		return new OtherConceptsDetailsEvent(otherConceptsDetails);
		
	}

	public OtherConceptsDetailsEvent modifyOtherConcepts(OtherConceptsModificationEvent event) throws Exception {
		OtherConcepts otherConcepts = new OtherConcepts().fromOtherConceptsDetails(event.getOtherConceptsDetails());
		otherConcepts = otherConceptsRepository.save(otherConcepts);
		otherConcepts = otherConceptsRepository.findOne(otherConcepts.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",otherConcepts.getId());
		return new OtherConceptsDetailsEvent(new OtherConceptsDetails().toOtherConceptsDetails(otherConcepts));
	}
	
		public OtherConceptsDetailsEvent getOnlyRecord() throws Exception {
			Iterable<OtherConcepts> all = otherConceptsRepository.findAll();
			OtherConceptsDetails single = new OtherConceptsDetails();
			for (OtherConcepts otherConcepts : all) {
				single=new OtherConceptsDetails().toOtherConceptsDetails(otherConcepts);
				break;
			}
			return new OtherConceptsDetailsEvent(single);
		}
	
	public OtherConceptsPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<OtherConcepts> all = otherConceptsCustomRepo.findAllActiveByCompany(fields);
			List<OtherConceptsDetails> list = new ArrayList<OtherConceptsDetails>();
			for (OtherConcepts otherConcepts : all) {
				list.add(new OtherConceptsDetails().toOtherConceptsDetails(otherConcepts, fields));
			}
			return new OtherConceptsPageEvent(list);
		}public OtherConceptsPageEvent requestAllByNameOtherconcepts(EmbeddedField... fields) throws Exception {
			List<OtherConcepts> all = otherConceptsCustomRepo.findAllActiveByNameOtherconcepts(fields);
			List<OtherConceptsDetails> list = new ArrayList<OtherConceptsDetails>();
			for (OtherConcepts otherConcepts : all) {
				list.add(new OtherConceptsDetails().toOtherConceptsDetails(otherConcepts, fields));
			}
			return new OtherConceptsPageEvent(list);
		}public OtherConceptsPageEvent requestAllByUnitMeasure(EmbeddedField... fields) throws Exception {
			List<OtherConcepts> all = otherConceptsCustomRepo.findAllActiveByUnitMeasure(fields);
			List<OtherConceptsDetails> list = new ArrayList<OtherConceptsDetails>();
			for (OtherConcepts otherConcepts : all) {
				list.add(new OtherConceptsDetails().toOtherConceptsDetails(otherConcepts, fields));
			}
			return new OtherConceptsPageEvent(list);
		}public OtherConceptsPageEvent requestAllByTypeWork(EmbeddedField... fields) throws Exception {
			List<OtherConcepts> all = otherConceptsCustomRepo.findAllActiveByTypeWork(fields);
			List<OtherConceptsDetails> list = new ArrayList<OtherConceptsDetails>();
			for (OtherConcepts otherConcepts : all) {
				list.add(new OtherConceptsDetails().toOtherConceptsDetails(otherConcepts, fields));
			}
			return new OtherConceptsPageEvent(list);
		}
	

	public OtherConceptsPageEvent requestOtherConceptsFilterPage(RequestOtherConceptsPageEvent event) throws Exception {
		Page<OtherConcepts> page = otherConceptsCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new OtherConceptsPageEvent(page, otherConceptsCustomRepo.getTotalCount());
	}
	
	public OtherConceptsPageEvent requestOtherConceptsFilter(
			RequestOtherConceptsPageEvent event) throws Exception {
		List<OtherConcepts> all = otherConceptsCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		OtherConceptsPageEvent pageEvent = new OtherConceptsPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}