package co.com.cybersoft.maintenance.tables.persistence.services.storeType;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.StoreTypeDetails;
import co.com.cybersoft.maintenance.tables.events.storeType.CreateStoreTypeEvent;
import co.com.cybersoft.maintenance.tables.events.storeType.StoreTypeDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.storeType.StoreTypePageEvent;
import co.com.cybersoft.maintenance.tables.events.storeType.StoreTypeModificationEvent;
import co.com.cybersoft.maintenance.tables.events.storeType.RequestStoreTypeDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.storeType.RequestStoreTypePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.StoreType;
import co.com.cybersoft.maintenance.tables.persistence.repository.storeType.StoreTypeRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.storeType.StoreTypeCustomRepo;
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
public class StoreTypePersistenceServiceImpl implements StoreTypePersistenceService{

	private final StoreTypeRepository storeTypeRepository;
	
	private final StoreTypeCustomRepo storeTypeCustomRepo;
	
	
	public StoreTypePersistenceServiceImpl(final StoreTypeRepository storeTypeRepository, final StoreTypeCustomRepo storeTypeCustomRepo) {
		this.storeTypeRepository=storeTypeRepository;
		this.storeTypeCustomRepo=storeTypeCustomRepo;
	}
	
	public StoreTypeDetailsEvent createStoreType(CreateStoreTypeEvent event) throws Exception{
		StoreType storeType = new StoreType().fromStoreTypeDetails(event.getStoreTypeDetails());
		storeType = storeTypeRepository.save(storeType);
		storeType = storeTypeRepository.findOne(storeType.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",storeType.getId());
		return new StoreTypeDetailsEvent(new StoreTypeDetails().toStoreTypeDetails(storeType));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"storeType", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public StoreTypePageEvent requestStoreTypePage(RequestStoreTypePageEvent event) throws Exception {
		Page<StoreType> storeTypes = storeTypeRepository.findAll(event.getPageable());
		return new StoreTypePageEvent(storeTypes);
	}

	public StoreTypeDetailsEvent requestStoreTypeDetails(RequestStoreTypeDetailsEvent event) throws Exception {
		StoreTypeDetails storeTypeDetails=null;
		if (event.getId()!=null){
			StoreType storeType = storeTypeRepository.findOne(event.getId());
			if (storeType!=null)
				storeTypeDetails = new StoreTypeDetails().toStoreTypeDetails(storeType);
		}
		return new StoreTypeDetailsEvent(storeTypeDetails);
		
	}

	public StoreTypeDetailsEvent modifyStoreType(StoreTypeModificationEvent event) throws Exception {
		StoreType storeType = new StoreType().fromStoreTypeDetails(event.getStoreTypeDetails());
		storeType = storeTypeRepository.save(storeType);
		storeType = storeTypeRepository.findOne(storeType.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",storeType.getId());
		return new StoreTypeDetailsEvent(new StoreTypeDetails().toStoreTypeDetails(storeType));
	}
	
		public StoreTypeDetailsEvent getOnlyRecord() throws Exception {
			Iterable<StoreType> all = storeTypeRepository.findAll();
			StoreTypeDetails single = new StoreTypeDetails();
			for (StoreType storeType : all) {
				single=new StoreTypeDetails().toStoreTypeDetails(storeType);
				break;
			}
			return new StoreTypeDetailsEvent(single);
		}
	
	public StoreTypePageEvent requestAllByStoreName(EmbeddedField... fields) throws Exception {
			List<StoreType> all = storeTypeCustomRepo.findAllActiveByStoreName(fields);
			List<StoreTypeDetails> list = new ArrayList<StoreTypeDetails>();
			for (StoreType storeType : all) {
				list.add(new StoreTypeDetails().toStoreTypeDetails(storeType, fields));
			}
			return new StoreTypePageEvent(list);
		}
	

	public StoreTypePageEvent requestStoreTypeFilterPage(RequestStoreTypePageEvent event) throws Exception {
		Page<StoreType> page = storeTypeCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new StoreTypePageEvent(page, storeTypeCustomRepo.getTotalCount());
	}
	
	public StoreTypePageEvent requestStoreTypeFilter(
			RequestStoreTypePageEvent event) throws Exception {
		List<StoreType> all = storeTypeCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		StoreTypePageEvent pageEvent = new StoreTypePageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}