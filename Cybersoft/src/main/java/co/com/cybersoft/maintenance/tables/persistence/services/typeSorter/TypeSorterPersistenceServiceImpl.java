package co.com.cybersoft.maintenance.tables.persistence.services.typeSorter;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.TypeSorterDetails;
import co.com.cybersoft.maintenance.tables.events.typeSorter.CreateTypeSorterEvent;
import co.com.cybersoft.maintenance.tables.events.typeSorter.TypeSorterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeSorter.TypeSorterPageEvent;
import co.com.cybersoft.maintenance.tables.events.typeSorter.TypeSorterModificationEvent;
import co.com.cybersoft.maintenance.tables.events.typeSorter.RequestTypeSorterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeSorter.RequestTypeSorterPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeSorter;
import co.com.cybersoft.maintenance.tables.persistence.repository.typeSorter.TypeSorterRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.typeSorter.TypeSorterCustomRepo;
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
public class TypeSorterPersistenceServiceImpl implements TypeSorterPersistenceService{

	private final TypeSorterRepository typeSorterRepository;
	
	private final TypeSorterCustomRepo typeSorterCustomRepo;
	
	
	public TypeSorterPersistenceServiceImpl(final TypeSorterRepository typeSorterRepository, final TypeSorterCustomRepo typeSorterCustomRepo) {
		this.typeSorterRepository=typeSorterRepository;
		this.typeSorterCustomRepo=typeSorterCustomRepo;
	}
	
	public TypeSorterDetailsEvent createTypeSorter(CreateTypeSorterEvent event) throws Exception{
		TypeSorter typeSorter = new TypeSorter().fromTypeSorterDetails(event.getTypeSorterDetails());
		typeSorter = typeSorterRepository.save(typeSorter);
		typeSorter = typeSorterRepository.findOne(typeSorter.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",typeSorter.getId());
		return new TypeSorterDetailsEvent(new TypeSorterDetails().toTypeSorterDetails(typeSorter));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"typeSorter", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public TypeSorterPageEvent requestTypeSorterPage(RequestTypeSorterPageEvent event) throws Exception {
		Page<TypeSorter> typeSorters = typeSorterRepository.findAll(event.getPageable());
		return new TypeSorterPageEvent(typeSorters);
	}

	public TypeSorterDetailsEvent requestTypeSorterDetails(RequestTypeSorterDetailsEvent event) throws Exception {
		TypeSorterDetails typeSorterDetails=null;
		if (event.getId()!=null){
			TypeSorter typeSorter = typeSorterRepository.findOne(event.getId());
			if (typeSorter!=null)
				typeSorterDetails = new TypeSorterDetails().toTypeSorterDetails(typeSorter);
		}
		return new TypeSorterDetailsEvent(typeSorterDetails);
		
	}

	public TypeSorterDetailsEvent modifyTypeSorter(TypeSorterModificationEvent event) throws Exception {
		TypeSorter typeSorter = new TypeSorter().fromTypeSorterDetails(event.getTypeSorterDetails());
		typeSorter = typeSorterRepository.save(typeSorter);
		typeSorter = typeSorterRepository.findOne(typeSorter.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",typeSorter.getId());
		return new TypeSorterDetailsEvent(new TypeSorterDetails().toTypeSorterDetails(typeSorter));
	}
	
		public TypeSorterDetailsEvent getOnlyRecord() throws Exception {
			Iterable<TypeSorter> all = typeSorterRepository.findAll();
			TypeSorterDetails single = new TypeSorterDetails();
			for (TypeSorter typeSorter : all) {
				single=new TypeSorterDetails().toTypeSorterDetails(typeSorter);
				break;
			}
			return new TypeSorterDetailsEvent(single);
		}
	
	public TypeSorterPageEvent requestAllByNameTypeSorter(EmbeddedField... fields) throws Exception {
			List<TypeSorter> all = typeSorterCustomRepo.findAllActiveByNameTypeSorter(fields);
			List<TypeSorterDetails> list = new ArrayList<TypeSorterDetails>();
			for (TypeSorter typeSorter : all) {
				list.add(new TypeSorterDetails().toTypeSorterDetails(typeSorter, fields));
			}
			return new TypeSorterPageEvent(list);
		}
	

	public TypeSorterPageEvent requestTypeSorterFilterPage(RequestTypeSorterPageEvent event) throws Exception {
		Page<TypeSorter> page = typeSorterCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new TypeSorterPageEvent(page, typeSorterCustomRepo.getTotalCount());
	}
	
	public TypeSorterPageEvent requestTypeSorterFilter(
			RequestTypeSorterPageEvent event) throws Exception {
		List<TypeSorter> all = typeSorterCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		TypeSorterPageEvent pageEvent = new TypeSorterPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}