package co.com.cybersoft.maintenance.tables.persistence.services.typeCauseClose;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.TypeCauseCloseDetails;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.CreateTypeCauseCloseEvent;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.TypeCauseCloseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.TypeCauseClosePageEvent;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.TypeCauseCloseModificationEvent;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.RequestTypeCauseCloseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.RequestTypeCauseClosePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeCauseClose;
import co.com.cybersoft.maintenance.tables.persistence.repository.typeCauseClose.TypeCauseCloseRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.typeCauseClose.TypeCauseCloseCustomRepo;
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
public class TypeCauseClosePersistenceServiceImpl implements TypeCauseClosePersistenceService{

	private final TypeCauseCloseRepository typeCauseCloseRepository;
	
	private final TypeCauseCloseCustomRepo typeCauseCloseCustomRepo;
	
	
	public TypeCauseClosePersistenceServiceImpl(final TypeCauseCloseRepository typeCauseCloseRepository, final TypeCauseCloseCustomRepo typeCauseCloseCustomRepo) {
		this.typeCauseCloseRepository=typeCauseCloseRepository;
		this.typeCauseCloseCustomRepo=typeCauseCloseCustomRepo;
	}
	
	public TypeCauseCloseDetailsEvent createTypeCauseClose(CreateTypeCauseCloseEvent event) throws Exception{
		TypeCauseClose typeCauseClose = new TypeCauseClose().fromTypeCauseCloseDetails(event.getTypeCauseCloseDetails());
		typeCauseClose = typeCauseCloseRepository.save(typeCauseClose);
		typeCauseClose = typeCauseCloseRepository.findOne(typeCauseClose.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",typeCauseClose.getId());
		return new TypeCauseCloseDetailsEvent(new TypeCauseCloseDetails().toTypeCauseCloseDetails(typeCauseClose));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"typeCauseClose", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public TypeCauseClosePageEvent requestTypeCauseClosePage(RequestTypeCauseClosePageEvent event) throws Exception {
		Page<TypeCauseClose> typeCauseCloses = typeCauseCloseRepository.findAll(event.getPageable());
		return new TypeCauseClosePageEvent(typeCauseCloses);
	}

	public TypeCauseCloseDetailsEvent requestTypeCauseCloseDetails(RequestTypeCauseCloseDetailsEvent event) throws Exception {
		TypeCauseCloseDetails typeCauseCloseDetails=null;
		if (event.getId()!=null){
			TypeCauseClose typeCauseClose = typeCauseCloseRepository.findOne(event.getId());
			if (typeCauseClose!=null)
				typeCauseCloseDetails = new TypeCauseCloseDetails().toTypeCauseCloseDetails(typeCauseClose);
		}
		return new TypeCauseCloseDetailsEvent(typeCauseCloseDetails);
		
	}

	public TypeCauseCloseDetailsEvent modifyTypeCauseClose(TypeCauseCloseModificationEvent event) throws Exception {
		TypeCauseClose typeCauseClose = new TypeCauseClose().fromTypeCauseCloseDetails(event.getTypeCauseCloseDetails());
		typeCauseClose = typeCauseCloseRepository.save(typeCauseClose);
		typeCauseClose = typeCauseCloseRepository.findOne(typeCauseClose.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",typeCauseClose.getId());
		return new TypeCauseCloseDetailsEvent(new TypeCauseCloseDetails().toTypeCauseCloseDetails(typeCauseClose));
	}
	
		public TypeCauseCloseDetailsEvent getOnlyRecord() throws Exception {
			Iterable<TypeCauseClose> all = typeCauseCloseRepository.findAll();
			TypeCauseCloseDetails single = new TypeCauseCloseDetails();
			for (TypeCauseClose typeCauseClose : all) {
				single=new TypeCauseCloseDetails().toTypeCauseCloseDetails(typeCauseClose);
				break;
			}
			return new TypeCauseCloseDetailsEvent(single);
		}
	
	public TypeCauseClosePageEvent requestAllByTypeCauseClose(EmbeddedField... fields) throws Exception {
			List<TypeCauseClose> all = typeCauseCloseCustomRepo.findAllActiveByTypeCauseClose(fields);
			List<TypeCauseCloseDetails> list = new ArrayList<TypeCauseCloseDetails>();
			for (TypeCauseClose typeCauseClose : all) {
				list.add(new TypeCauseCloseDetails().toTypeCauseCloseDetails(typeCauseClose, fields));
			}
			return new TypeCauseClosePageEvent(list);
		}
	

	public TypeCauseClosePageEvent requestTypeCauseCloseFilterPage(RequestTypeCauseClosePageEvent event) throws Exception {
		Page<TypeCauseClose> page = typeCauseCloseCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new TypeCauseClosePageEvent(page, typeCauseCloseCustomRepo.getTotalCount());
	}
	
	public TypeCauseClosePageEvent requestTypeCauseCloseFilter(
			RequestTypeCauseClosePageEvent event) throws Exception {
		List<TypeCauseClose> all = typeCauseCloseCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		TypeCauseClosePageEvent pageEvent = new TypeCauseClosePageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}