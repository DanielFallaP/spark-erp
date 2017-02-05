package co.com.cybersoft.maintenance.tables.persistence.services.typeConceptKardex;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.TypeConceptKardexDetails;
import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.CreateTypeConceptKardexEvent;
import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.TypeConceptKardexDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.TypeConceptKardexPageEvent;
import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.TypeConceptKardexModificationEvent;
import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.RequestTypeConceptKardexDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.RequestTypeConceptKardexPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeConceptKardex;
import co.com.cybersoft.maintenance.tables.persistence.repository.typeConceptKardex.TypeConceptKardexRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.typeConceptKardex.TypeConceptKardexCustomRepo;
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
public class TypeConceptKardexPersistenceServiceImpl implements TypeConceptKardexPersistenceService{

	private final TypeConceptKardexRepository typeConceptKardexRepository;
	
	private final TypeConceptKardexCustomRepo typeConceptKardexCustomRepo;
	
	
	public TypeConceptKardexPersistenceServiceImpl(final TypeConceptKardexRepository typeConceptKardexRepository, final TypeConceptKardexCustomRepo typeConceptKardexCustomRepo) {
		this.typeConceptKardexRepository=typeConceptKardexRepository;
		this.typeConceptKardexCustomRepo=typeConceptKardexCustomRepo;
	}
	
	public TypeConceptKardexDetailsEvent createTypeConceptKardex(CreateTypeConceptKardexEvent event) throws Exception{
		TypeConceptKardex typeConceptKardex = new TypeConceptKardex().fromTypeConceptKardexDetails(event.getTypeConceptKardexDetails());
		typeConceptKardex = typeConceptKardexRepository.save(typeConceptKardex);
		typeConceptKardex = typeConceptKardexRepository.findOne(typeConceptKardex.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",typeConceptKardex.getId());
		return new TypeConceptKardexDetailsEvent(new TypeConceptKardexDetails().toTypeConceptKardexDetails(typeConceptKardex));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"typeConceptKardex", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public TypeConceptKardexPageEvent requestTypeConceptKardexPage(RequestTypeConceptKardexPageEvent event) throws Exception {
		Page<TypeConceptKardex> typeConceptKardexs = typeConceptKardexRepository.findAll(event.getPageable());
		return new TypeConceptKardexPageEvent(typeConceptKardexs);
	}

	public TypeConceptKardexDetailsEvent requestTypeConceptKardexDetails(RequestTypeConceptKardexDetailsEvent event) throws Exception {
		TypeConceptKardexDetails typeConceptKardexDetails=null;
		if (event.getId()!=null){
			TypeConceptKardex typeConceptKardex = typeConceptKardexRepository.findOne(event.getId());
			if (typeConceptKardex!=null)
				typeConceptKardexDetails = new TypeConceptKardexDetails().toTypeConceptKardexDetails(typeConceptKardex);
		}
		return new TypeConceptKardexDetailsEvent(typeConceptKardexDetails);
		
	}

	public TypeConceptKardexDetailsEvent modifyTypeConceptKardex(TypeConceptKardexModificationEvent event) throws Exception {
		TypeConceptKardex typeConceptKardex = new TypeConceptKardex().fromTypeConceptKardexDetails(event.getTypeConceptKardexDetails());
		typeConceptKardex = typeConceptKardexRepository.save(typeConceptKardex);
		typeConceptKardex = typeConceptKardexRepository.findOne(typeConceptKardex.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",typeConceptKardex.getId());
		return new TypeConceptKardexDetailsEvent(new TypeConceptKardexDetails().toTypeConceptKardexDetails(typeConceptKardex));
	}
	
		public TypeConceptKardexDetailsEvent getOnlyRecord() throws Exception {
			Iterable<TypeConceptKardex> all = typeConceptKardexRepository.findAll();
			TypeConceptKardexDetails single = new TypeConceptKardexDetails();
			for (TypeConceptKardex typeConceptKardex : all) {
				single=new TypeConceptKardexDetails().toTypeConceptKardexDetails(typeConceptKardex);
				break;
			}
			return new TypeConceptKardexDetailsEvent(single);
		}
	
	public TypeConceptKardexPageEvent requestAllByTypeConceptKardex(EmbeddedField... fields) throws Exception {
			List<TypeConceptKardex> all = typeConceptKardexCustomRepo.findAllActiveByTypeConceptKardex(fields);
			List<TypeConceptKardexDetails> list = new ArrayList<TypeConceptKardexDetails>();
			for (TypeConceptKardex typeConceptKardex : all) {
				list.add(new TypeConceptKardexDetails().toTypeConceptKardexDetails(typeConceptKardex, fields));
			}
			return new TypeConceptKardexPageEvent(list);
		}
	

	public TypeConceptKardexPageEvent requestTypeConceptKardexFilterPage(RequestTypeConceptKardexPageEvent event) throws Exception {
		Page<TypeConceptKardex> page = typeConceptKardexCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new TypeConceptKardexPageEvent(page, typeConceptKardexCustomRepo.getTotalCount());
	}
	
	public TypeConceptKardexPageEvent requestTypeConceptKardexFilter(
			RequestTypeConceptKardexPageEvent event) throws Exception {
		List<TypeConceptKardex> all = typeConceptKardexCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		TypeConceptKardexPageEvent pageEvent = new TypeConceptKardexPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}