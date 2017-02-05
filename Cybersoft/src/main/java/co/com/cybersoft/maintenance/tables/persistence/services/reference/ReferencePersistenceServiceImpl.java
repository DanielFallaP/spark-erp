package co.com.cybersoft.maintenance.tables.persistence.services.reference;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.ReferenceDetails;
import co.com.cybersoft.maintenance.tables.events.reference.CreateReferenceEvent;
import co.com.cybersoft.maintenance.tables.events.reference.ReferenceDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.reference.ReferencePageEvent;
import co.com.cybersoft.maintenance.tables.events.reference.ReferenceModificationEvent;
import co.com.cybersoft.maintenance.tables.events.reference.RequestReferenceDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.reference.RequestReferencePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Reference;
import co.com.cybersoft.maintenance.tables.persistence.repository.reference.ReferenceRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.reference.ReferenceCustomRepo;
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
public class ReferencePersistenceServiceImpl implements ReferencePersistenceService{

	private final ReferenceRepository referenceRepository;
	
	private final ReferenceCustomRepo referenceCustomRepo;
	
	
	public ReferencePersistenceServiceImpl(final ReferenceRepository referenceRepository, final ReferenceCustomRepo referenceCustomRepo) {
		this.referenceRepository=referenceRepository;
		this.referenceCustomRepo=referenceCustomRepo;
	}
	
	public ReferenceDetailsEvent createReference(CreateReferenceEvent event) throws Exception{
		Reference reference = new Reference().fromReferenceDetails(event.getReferenceDetails());
		reference = referenceRepository.save(reference);
		reference = referenceRepository.findOne(reference.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",reference.getId());
		return new ReferenceDetailsEvent(new ReferenceDetails().toReferenceDetails(reference));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"reference", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public ReferencePageEvent requestReferencePage(RequestReferencePageEvent event) throws Exception {
		Page<Reference> references = referenceRepository.findAll(event.getPageable());
		return new ReferencePageEvent(references);
	}

	public ReferenceDetailsEvent requestReferenceDetails(RequestReferenceDetailsEvent event) throws Exception {
		ReferenceDetails referenceDetails=null;
		if (event.getId()!=null){
			Reference reference = referenceRepository.findOne(event.getId());
			if (reference!=null)
				referenceDetails = new ReferenceDetails().toReferenceDetails(reference);
		}
		return new ReferenceDetailsEvent(referenceDetails);
		
	}

	public ReferenceDetailsEvent modifyReference(ReferenceModificationEvent event) throws Exception {
		Reference reference = new Reference().fromReferenceDetails(event.getReferenceDetails());
		reference = referenceRepository.save(reference);
		reference = referenceRepository.findOne(reference.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",reference.getId());
		return new ReferenceDetailsEvent(new ReferenceDetails().toReferenceDetails(reference));
	}
	
		public ReferenceDetailsEvent getOnlyRecord() throws Exception {
			Iterable<Reference> all = referenceRepository.findAll();
			ReferenceDetails single = new ReferenceDetails();
			for (Reference reference : all) {
				single=new ReferenceDetails().toReferenceDetails(reference);
				break;
			}
			return new ReferenceDetailsEvent(single);
		}
	
	public ReferencePageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<Reference> all = referenceCustomRepo.findAllActiveByCompany(fields);
			List<ReferenceDetails> list = new ArrayList<ReferenceDetails>();
			for (Reference reference : all) {
				list.add(new ReferenceDetails().toReferenceDetails(reference, fields));
			}
			return new ReferencePageEvent(list);
		}public ReferencePageEvent requestAllByCodeReference(EmbeddedField... fields) throws Exception {
			List<Reference> all = referenceCustomRepo.findAllActiveByCodeReference(fields);
			List<ReferenceDetails> list = new ArrayList<ReferenceDetails>();
			for (Reference reference : all) {
				list.add(new ReferenceDetails().toReferenceDetails(reference, fields));
			}
			return new ReferencePageEvent(list);
		}public ReferencePageEvent requestAllByNameReference(EmbeddedField... fields) throws Exception {
			List<Reference> all = referenceCustomRepo.findAllActiveByNameReference(fields);
			List<ReferenceDetails> list = new ArrayList<ReferenceDetails>();
			for (Reference reference : all) {
				list.add(new ReferenceDetails().toReferenceDetails(reference, fields));
			}
			return new ReferencePageEvent(list);
		}
	

	public ReferencePageEvent requestReferenceFilterPage(RequestReferencePageEvent event) throws Exception {
		Page<Reference> page = referenceCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new ReferencePageEvent(page, referenceCustomRepo.getTotalCount());
	}
	
	public ReferencePageEvent requestReferenceFilter(
			RequestReferencePageEvent event) throws Exception {
		List<Reference> all = referenceCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		ReferencePageEvent pageEvent = new ReferencePageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}