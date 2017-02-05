package co.com.cybersoft.maintenance.tables.persistence.services.operation;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.OperationDetails;
import co.com.cybersoft.maintenance.tables.events.operation.CreateOperationEvent;
import co.com.cybersoft.maintenance.tables.events.operation.OperationDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.operation.OperationPageEvent;
import co.com.cybersoft.maintenance.tables.events.operation.OperationModificationEvent;
import co.com.cybersoft.maintenance.tables.events.operation.RequestOperationDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.operation.RequestOperationPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Operation;
import co.com.cybersoft.maintenance.tables.persistence.repository.operation.OperationRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.operation.OperationCustomRepo;
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
public class OperationPersistenceServiceImpl implements OperationPersistenceService{

	private final OperationRepository operationRepository;
	
	private final OperationCustomRepo operationCustomRepo;
	
	
	public OperationPersistenceServiceImpl(final OperationRepository operationRepository, final OperationCustomRepo operationCustomRepo) {
		this.operationRepository=operationRepository;
		this.operationCustomRepo=operationCustomRepo;
	}
	
	public OperationDetailsEvent createOperation(CreateOperationEvent event) throws Exception{
		Operation operation = new Operation().fromOperationDetails(event.getOperationDetails());
		operation = operationRepository.save(operation);
		operation = operationRepository.findOne(operation.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",operation.getId());
		return new OperationDetailsEvent(new OperationDetails().toOperationDetails(operation));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"operation", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public OperationPageEvent requestOperationPage(RequestOperationPageEvent event) throws Exception {
		Page<Operation> operations = operationRepository.findAll(event.getPageable());
		return new OperationPageEvent(operations);
	}

	public OperationDetailsEvent requestOperationDetails(RequestOperationDetailsEvent event) throws Exception {
		OperationDetails operationDetails=null;
		if (event.getId()!=null){
			Operation operation = operationRepository.findOne(event.getId());
			if (operation!=null)
				operationDetails = new OperationDetails().toOperationDetails(operation);
		}
		return new OperationDetailsEvent(operationDetails);
		
	}

	public OperationDetailsEvent modifyOperation(OperationModificationEvent event) throws Exception {
		Operation operation = new Operation().fromOperationDetails(event.getOperationDetails());
		operation = operationRepository.save(operation);
		operation = operationRepository.findOne(operation.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",operation.getId());
		return new OperationDetailsEvent(new OperationDetails().toOperationDetails(operation));
	}
	
		public OperationDetailsEvent getOnlyRecord() throws Exception {
			Iterable<Operation> all = operationRepository.findAll();
			OperationDetails single = new OperationDetails();
			for (Operation operation : all) {
				single=new OperationDetails().toOperationDetails(operation);
				break;
			}
			return new OperationDetailsEvent(single);
		}
	
	public OperationPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<Operation> all = operationCustomRepo.findAllActiveByCompany(fields);
			List<OperationDetails> list = new ArrayList<OperationDetails>();
			for (Operation operation : all) {
				list.add(new OperationDetails().toOperationDetails(operation, fields));
			}
			return new OperationPageEvent(list);
		}public OperationPageEvent requestAllByCodeOperation(EmbeddedField... fields) throws Exception {
			List<Operation> all = operationCustomRepo.findAllActiveByCodeOperation(fields);
			List<OperationDetails> list = new ArrayList<OperationDetails>();
			for (Operation operation : all) {
				list.add(new OperationDetails().toOperationDetails(operation, fields));
			}
			return new OperationPageEvent(list);
		}public OperationPageEvent requestAllByNameOperation(EmbeddedField... fields) throws Exception {
			List<Operation> all = operationCustomRepo.findAllActiveByNameOperation(fields);
			List<OperationDetails> list = new ArrayList<OperationDetails>();
			for (Operation operation : all) {
				list.add(new OperationDetails().toOperationDetails(operation, fields));
			}
			return new OperationPageEvent(list);
		}
	

	public OperationPageEvent requestOperationFilterPage(RequestOperationPageEvent event) throws Exception {
		Page<Operation> page = operationCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new OperationPageEvent(page, operationCustomRepo.getTotalCount());
	}
	
	public OperationPageEvent requestOperationFilter(
			RequestOperationPageEvent event) throws Exception {
		List<Operation> all = operationCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		OperationPageEvent pageEvent = new OperationPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}