package co.com.cybersoft.maintenance.tables.persistence.services.conceptKardex;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.ConceptKardexDetails;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.CreateConceptKardexEvent;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.ConceptKardexDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.ConceptKardexPageEvent;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.ConceptKardexModificationEvent;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.RequestConceptKardexDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.RequestConceptKardexPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.ConceptKardex;
import co.com.cybersoft.maintenance.tables.persistence.repository.conceptKardex.ConceptKardexRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.conceptKardex.ConceptKardexCustomRepo;
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
public class ConceptKardexPersistenceServiceImpl implements ConceptKardexPersistenceService{

	private final ConceptKardexRepository conceptKardexRepository;
	
	private final ConceptKardexCustomRepo conceptKardexCustomRepo;
	
	
	public ConceptKardexPersistenceServiceImpl(final ConceptKardexRepository conceptKardexRepository, final ConceptKardexCustomRepo conceptKardexCustomRepo) {
		this.conceptKardexRepository=conceptKardexRepository;
		this.conceptKardexCustomRepo=conceptKardexCustomRepo;
	}
	
	public ConceptKardexDetailsEvent createConceptKardex(CreateConceptKardexEvent event) throws Exception{
		ConceptKardex conceptKardex = new ConceptKardex().fromConceptKardexDetails(event.getConceptKardexDetails());
		conceptKardex = conceptKardexRepository.save(conceptKardex);
		conceptKardex = conceptKardexRepository.findOne(conceptKardex.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",conceptKardex.getId());
		return new ConceptKardexDetailsEvent(new ConceptKardexDetails().toConceptKardexDetails(conceptKardex));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"conceptKardex", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public ConceptKardexPageEvent requestConceptKardexPage(RequestConceptKardexPageEvent event) throws Exception {
		Page<ConceptKardex> conceptKardexs = conceptKardexRepository.findAll(event.getPageable());
		return new ConceptKardexPageEvent(conceptKardexs);
	}

	public ConceptKardexDetailsEvent requestConceptKardexDetails(RequestConceptKardexDetailsEvent event) throws Exception {
		ConceptKardexDetails conceptKardexDetails=null;
		if (event.getId()!=null){
			ConceptKardex conceptKardex = conceptKardexRepository.findOne(event.getId());
			if (conceptKardex!=null)
				conceptKardexDetails = new ConceptKardexDetails().toConceptKardexDetails(conceptKardex);
		}
		return new ConceptKardexDetailsEvent(conceptKardexDetails);
		
	}

	public ConceptKardexDetailsEvent modifyConceptKardex(ConceptKardexModificationEvent event) throws Exception {
		ConceptKardex conceptKardex = new ConceptKardex().fromConceptKardexDetails(event.getConceptKardexDetails());
		conceptKardex = conceptKardexRepository.save(conceptKardex);
		conceptKardex = conceptKardexRepository.findOne(conceptKardex.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",conceptKardex.getId());
		return new ConceptKardexDetailsEvent(new ConceptKardexDetails().toConceptKardexDetails(conceptKardex));
	}
	
		public ConceptKardexDetailsEvent getOnlyRecord() throws Exception {
			Iterable<ConceptKardex> all = conceptKardexRepository.findAll();
			ConceptKardexDetails single = new ConceptKardexDetails();
			for (ConceptKardex conceptKardex : all) {
				single=new ConceptKardexDetails().toConceptKardexDetails(conceptKardex);
				break;
			}
			return new ConceptKardexDetailsEvent(single);
		}
	
	public ConceptKardexPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<ConceptKardex> all = conceptKardexCustomRepo.findAllActiveByCompany(fields);
			List<ConceptKardexDetails> list = new ArrayList<ConceptKardexDetails>();
			for (ConceptKardex conceptKardex : all) {
				list.add(new ConceptKardexDetails().toConceptKardexDetails(conceptKardex, fields));
			}
			return new ConceptKardexPageEvent(list);
		}public ConceptKardexPageEvent requestAllByStore(EmbeddedField... fields) throws Exception {
			List<ConceptKardex> all = conceptKardexCustomRepo.findAllActiveByStore(fields);
			List<ConceptKardexDetails> list = new ArrayList<ConceptKardexDetails>();
			for (ConceptKardex conceptKardex : all) {
				list.add(new ConceptKardexDetails().toConceptKardexDetails(conceptKardex, fields));
			}
			return new ConceptKardexPageEvent(list);
		}public ConceptKardexPageEvent requestAllByTypeConceptKardex(EmbeddedField... fields) throws Exception {
			List<ConceptKardex> all = conceptKardexCustomRepo.findAllActiveByTypeConceptKardex(fields);
			List<ConceptKardexDetails> list = new ArrayList<ConceptKardexDetails>();
			for (ConceptKardex conceptKardex : all) {
				list.add(new ConceptKardexDetails().toConceptKardexDetails(conceptKardex, fields));
			}
			return new ConceptKardexPageEvent(list);
		}
	

	public ConceptKardexPageEvent requestConceptKardexFilterPage(RequestConceptKardexPageEvent event) throws Exception {
		Page<ConceptKardex> page = conceptKardexCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new ConceptKardexPageEvent(page, conceptKardexCustomRepo.getTotalCount());
	}
	
	public ConceptKardexPageEvent requestConceptKardexFilter(
			RequestConceptKardexPageEvent event) throws Exception {
		List<ConceptKardex> all = conceptKardexCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		ConceptKardexPageEvent pageEvent = new ConceptKardexPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}