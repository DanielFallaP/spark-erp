package co.com.cybersoft.maintenance.tables.persistence.services.referenceOperation;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.ReferenceOperationDetails;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.CreateReferenceOperationEvent;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.ReferenceOperationDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.ReferenceOperationPageEvent;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.ReferenceOperationModificationEvent;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.RequestReferenceOperationDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.RequestReferenceOperationPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.ReferenceOperation;
import co.com.cybersoft.maintenance.tables.persistence.repository.referenceOperation.ReferenceOperationRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.referenceOperation.ReferenceOperationCustomRepo;
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
public class ReferenceOperationPersistenceServiceImpl implements ReferenceOperationPersistenceService{

	private final ReferenceOperationRepository referenceOperationRepository;
	
	private final ReferenceOperationCustomRepo referenceOperationCustomRepo;
	
	
	public ReferenceOperationPersistenceServiceImpl(final ReferenceOperationRepository referenceOperationRepository, final ReferenceOperationCustomRepo referenceOperationCustomRepo) {
		this.referenceOperationRepository=referenceOperationRepository;
		this.referenceOperationCustomRepo=referenceOperationCustomRepo;
	}
	
	public ReferenceOperationDetailsEvent createReferenceOperation(CreateReferenceOperationEvent event) throws Exception{
		ReferenceOperation referenceOperation = new ReferenceOperation().fromReferenceOperationDetails(event.getReferenceOperationDetails());
		referenceOperation = referenceOperationRepository.save(referenceOperation);
		referenceOperation = referenceOperationRepository.findOne(referenceOperation.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",referenceOperation.getId());
		return new ReferenceOperationDetailsEvent(new ReferenceOperationDetails().toReferenceOperationDetails(referenceOperation));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"referenceOperation", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public ReferenceOperationPageEvent requestReferenceOperationPage(RequestReferenceOperationPageEvent event) throws Exception {
		Page<ReferenceOperation> referenceOperations = referenceOperationRepository.findAll(event.getPageable());
		return new ReferenceOperationPageEvent(referenceOperations);
	}

	public ReferenceOperationDetailsEvent requestReferenceOperationDetails(RequestReferenceOperationDetailsEvent event) throws Exception {
		ReferenceOperationDetails referenceOperationDetails=null;
		if (event.getId()!=null){
			ReferenceOperation referenceOperation = referenceOperationRepository.findOne(event.getId());
			if (referenceOperation!=null)
				referenceOperationDetails = new ReferenceOperationDetails().toReferenceOperationDetails(referenceOperation);
		}
		return new ReferenceOperationDetailsEvent(referenceOperationDetails);
		
	}

	public ReferenceOperationDetailsEvent modifyReferenceOperation(ReferenceOperationModificationEvent event) throws Exception {
		ReferenceOperation referenceOperation = new ReferenceOperation().fromReferenceOperationDetails(event.getReferenceOperationDetails());
		referenceOperation = referenceOperationRepository.save(referenceOperation);
		referenceOperation = referenceOperationRepository.findOne(referenceOperation.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",referenceOperation.getId());
		return new ReferenceOperationDetailsEvent(new ReferenceOperationDetails().toReferenceOperationDetails(referenceOperation));
	}
	
		public ReferenceOperationDetailsEvent getOnlyRecord() throws Exception {
			Iterable<ReferenceOperation> all = referenceOperationRepository.findAll();
			ReferenceOperationDetails single = new ReferenceOperationDetails();
			for (ReferenceOperation referenceOperation : all) {
				single=new ReferenceOperationDetails().toReferenceOperationDetails(referenceOperation);
				break;
			}
			return new ReferenceOperationDetailsEvent(single);
		}
	
	public ReferenceOperationPageEvent requestAllByReference(EmbeddedField... fields) throws Exception {
			List<ReferenceOperation> all = referenceOperationCustomRepo.findAllActiveByReference(fields);
			List<ReferenceOperationDetails> list = new ArrayList<ReferenceOperationDetails>();
			for (ReferenceOperation referenceOperation : all) {
				list.add(new ReferenceOperationDetails().toReferenceOperationDetails(referenceOperation, fields));
			}
			return new ReferenceOperationPageEvent(list);
		}public ReferenceOperationPageEvent requestAllByOperation(EmbeddedField... fields) throws Exception {
			List<ReferenceOperation> all = referenceOperationCustomRepo.findAllActiveByOperation(fields);
			List<ReferenceOperationDetails> list = new ArrayList<ReferenceOperationDetails>();
			for (ReferenceOperation referenceOperation : all) {
				list.add(new ReferenceOperationDetails().toReferenceOperationDetails(referenceOperation, fields));
			}
			return new ReferenceOperationPageEvent(list);
		}
	

	public ReferenceOperationPageEvent requestReferenceOperationFilterPage(RequestReferenceOperationPageEvent event) throws Exception {
		Page<ReferenceOperation> page = referenceOperationCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new ReferenceOperationPageEvent(page, referenceOperationCustomRepo.getTotalCount());
	}
	
	public ReferenceOperationPageEvent requestReferenceOperationFilter(
			RequestReferenceOperationPageEvent event) throws Exception {
		List<ReferenceOperation> all = referenceOperationCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		ReferenceOperationPageEvent pageEvent = new ReferenceOperationPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}