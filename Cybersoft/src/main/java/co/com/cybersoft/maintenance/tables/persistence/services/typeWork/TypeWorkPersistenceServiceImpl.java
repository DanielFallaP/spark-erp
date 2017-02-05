package co.com.cybersoft.maintenance.tables.persistence.services.typeWork;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.TypeWorkDetails;
import co.com.cybersoft.maintenance.tables.events.typeWork.CreateTypeWorkEvent;
import co.com.cybersoft.maintenance.tables.events.typeWork.TypeWorkDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeWork.TypeWorkPageEvent;
import co.com.cybersoft.maintenance.tables.events.typeWork.TypeWorkModificationEvent;
import co.com.cybersoft.maintenance.tables.events.typeWork.RequestTypeWorkDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeWork.RequestTypeWorkPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeWork;
import co.com.cybersoft.maintenance.tables.persistence.repository.typeWork.TypeWorkRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.typeWork.TypeWorkCustomRepo;
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
public class TypeWorkPersistenceServiceImpl implements TypeWorkPersistenceService{

	private final TypeWorkRepository typeWorkRepository;
	
	private final TypeWorkCustomRepo typeWorkCustomRepo;
	
	
	public TypeWorkPersistenceServiceImpl(final TypeWorkRepository typeWorkRepository, final TypeWorkCustomRepo typeWorkCustomRepo) {
		this.typeWorkRepository=typeWorkRepository;
		this.typeWorkCustomRepo=typeWorkCustomRepo;
	}
	
	public TypeWorkDetailsEvent createTypeWork(CreateTypeWorkEvent event) throws Exception{
		TypeWork typeWork = new TypeWork().fromTypeWorkDetails(event.getTypeWorkDetails());
		typeWork = typeWorkRepository.save(typeWork);
		typeWork = typeWorkRepository.findOne(typeWork.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",typeWork.getId());
		return new TypeWorkDetailsEvent(new TypeWorkDetails().toTypeWorkDetails(typeWork));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"typeWork", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public TypeWorkPageEvent requestTypeWorkPage(RequestTypeWorkPageEvent event) throws Exception {
		Page<TypeWork> typeWorks = typeWorkRepository.findAll(event.getPageable());
		return new TypeWorkPageEvent(typeWorks);
	}

	public TypeWorkDetailsEvent requestTypeWorkDetails(RequestTypeWorkDetailsEvent event) throws Exception {
		TypeWorkDetails typeWorkDetails=null;
		if (event.getId()!=null){
			TypeWork typeWork = typeWorkRepository.findOne(event.getId());
			if (typeWork!=null)
				typeWorkDetails = new TypeWorkDetails().toTypeWorkDetails(typeWork);
		}
		return new TypeWorkDetailsEvent(typeWorkDetails);
		
	}

	public TypeWorkDetailsEvent modifyTypeWork(TypeWorkModificationEvent event) throws Exception {
		TypeWork typeWork = new TypeWork().fromTypeWorkDetails(event.getTypeWorkDetails());
		typeWork = typeWorkRepository.save(typeWork);
		typeWork = typeWorkRepository.findOne(typeWork.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",typeWork.getId());
		return new TypeWorkDetailsEvent(new TypeWorkDetails().toTypeWorkDetails(typeWork));
	}
	
		public TypeWorkDetailsEvent getOnlyRecord() throws Exception {
			Iterable<TypeWork> all = typeWorkRepository.findAll();
			TypeWorkDetails single = new TypeWorkDetails();
			for (TypeWork typeWork : all) {
				single=new TypeWorkDetails().toTypeWorkDetails(typeWork);
				break;
			}
			return new TypeWorkDetailsEvent(single);
		}
	
	public TypeWorkPageEvent requestAllByTypeWork(EmbeddedField... fields) throws Exception {
			List<TypeWork> all = typeWorkCustomRepo.findAllActiveByTypeWork(fields);
			List<TypeWorkDetails> list = new ArrayList<TypeWorkDetails>();
			for (TypeWork typeWork : all) {
				list.add(new TypeWorkDetails().toTypeWorkDetails(typeWork, fields));
			}
			return new TypeWorkPageEvent(list);
		}
	

	public TypeWorkPageEvent requestTypeWorkFilterPage(RequestTypeWorkPageEvent event) throws Exception {
		Page<TypeWork> page = typeWorkCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new TypeWorkPageEvent(page, typeWorkCustomRepo.getTotalCount());
	}
	
	public TypeWorkPageEvent requestTypeWorkFilter(
			RequestTypeWorkPageEvent event) throws Exception {
		List<TypeWork> all = typeWorkCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		TypeWorkPageEvent pageEvent = new TypeWorkPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}