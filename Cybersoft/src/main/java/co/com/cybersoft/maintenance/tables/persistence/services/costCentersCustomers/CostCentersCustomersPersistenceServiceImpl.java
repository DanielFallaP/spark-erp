package co.com.cybersoft.maintenance.tables.persistence.services.costCentersCustomers;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.CostCentersCustomersDetails;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.CreateCostCentersCustomersEvent;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.CostCentersCustomersDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.CostCentersCustomersPageEvent;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.CostCentersCustomersModificationEvent;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.RequestCostCentersCustomersDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.RequestCostCentersCustomersPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.CostCentersCustomers;
import co.com.cybersoft.maintenance.tables.persistence.repository.costCentersCustomers.CostCentersCustomersRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.costCentersCustomers.CostCentersCustomersCustomRepo;
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
public class CostCentersCustomersPersistenceServiceImpl implements CostCentersCustomersPersistenceService{

	private final CostCentersCustomersRepository costCentersCustomersRepository;
	
	private final CostCentersCustomersCustomRepo costCentersCustomersCustomRepo;
	
	
	public CostCentersCustomersPersistenceServiceImpl(final CostCentersCustomersRepository costCentersCustomersRepository, final CostCentersCustomersCustomRepo costCentersCustomersCustomRepo) {
		this.costCentersCustomersRepository=costCentersCustomersRepository;
		this.costCentersCustomersCustomRepo=costCentersCustomersCustomRepo;
	}
	
	public CostCentersCustomersDetailsEvent createCostCentersCustomers(CreateCostCentersCustomersEvent event) throws Exception{
		CostCentersCustomers costCentersCustomers = new CostCentersCustomers().fromCostCentersCustomersDetails(event.getCostCentersCustomersDetails());
		costCentersCustomers = costCentersCustomersRepository.save(costCentersCustomers);
		costCentersCustomers = costCentersCustomersRepository.findOne(costCentersCustomers.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",costCentersCustomers.getId());
		return new CostCentersCustomersDetailsEvent(new CostCentersCustomersDetails().toCostCentersCustomersDetails(costCentersCustomers));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"costCentersCustomers", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public CostCentersCustomersPageEvent requestCostCentersCustomersPage(RequestCostCentersCustomersPageEvent event) throws Exception {
		Page<CostCentersCustomers> costCentersCustomerss = costCentersCustomersRepository.findAll(event.getPageable());
		return new CostCentersCustomersPageEvent(costCentersCustomerss);
	}

	public CostCentersCustomersDetailsEvent requestCostCentersCustomersDetails(RequestCostCentersCustomersDetailsEvent event) throws Exception {
		CostCentersCustomersDetails costCentersCustomersDetails=null;
		if (event.getId()!=null){
			CostCentersCustomers costCentersCustomers = costCentersCustomersRepository.findOne(event.getId());
			if (costCentersCustomers!=null)
				costCentersCustomersDetails = new CostCentersCustomersDetails().toCostCentersCustomersDetails(costCentersCustomers);
		}
		return new CostCentersCustomersDetailsEvent(costCentersCustomersDetails);
		
	}

	public CostCentersCustomersDetailsEvent modifyCostCentersCustomers(CostCentersCustomersModificationEvent event) throws Exception {
		CostCentersCustomers costCentersCustomers = new CostCentersCustomers().fromCostCentersCustomersDetails(event.getCostCentersCustomersDetails());
		costCentersCustomers = costCentersCustomersRepository.save(costCentersCustomers);
		costCentersCustomers = costCentersCustomersRepository.findOne(costCentersCustomers.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",costCentersCustomers.getId());
		return new CostCentersCustomersDetailsEvent(new CostCentersCustomersDetails().toCostCentersCustomersDetails(costCentersCustomers));
	}
	
		public CostCentersCustomersDetailsEvent getOnlyRecord() throws Exception {
			Iterable<CostCentersCustomers> all = costCentersCustomersRepository.findAll();
			CostCentersCustomersDetails single = new CostCentersCustomersDetails();
			for (CostCentersCustomers costCentersCustomers : all) {
				single=new CostCentersCustomersDetails().toCostCentersCustomersDetails(costCentersCustomers);
				break;
			}
			return new CostCentersCustomersDetailsEvent(single);
		}
	
	public CostCentersCustomersPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<CostCentersCustomers> all = costCentersCustomersCustomRepo.findAllActiveByCompany(fields);
			List<CostCentersCustomersDetails> list = new ArrayList<CostCentersCustomersDetails>();
			for (CostCentersCustomers costCentersCustomers : all) {
				list.add(new CostCentersCustomersDetails().toCostCentersCustomersDetails(costCentersCustomers, fields));
			}
			return new CostCentersCustomersPageEvent(list);
		}public CostCentersCustomersPageEvent requestAllByDescription(EmbeddedField... fields) throws Exception {
			List<CostCentersCustomers> all = costCentersCustomersCustomRepo.findAllActiveByDescription(fields);
			List<CostCentersCustomersDetails> list = new ArrayList<CostCentersCustomersDetails>();
			for (CostCentersCustomers costCentersCustomers : all) {
				list.add(new CostCentersCustomersDetails().toCostCentersCustomersDetails(costCentersCustomers, fields));
			}
			return new CostCentersCustomersPageEvent(list);
		}public CostCentersCustomersPageEvent requestAllByNameCostCenter(EmbeddedField... fields) throws Exception {
			List<CostCentersCustomers> all = costCentersCustomersCustomRepo.findAllActiveByNameCostCenter(fields);
			List<CostCentersCustomersDetails> list = new ArrayList<CostCentersCustomersDetails>();
			for (CostCentersCustomers costCentersCustomers : all) {
				list.add(new CostCentersCustomersDetails().toCostCentersCustomersDetails(costCentersCustomers, fields));
			}
			return new CostCentersCustomersPageEvent(list);
		}public CostCentersCustomersPageEvent requestAllBySubCostCentersCustomers(EmbeddedField... fields) throws Exception {
			List<CostCentersCustomers> all = costCentersCustomersCustomRepo.findAllActiveBySubCostCentersCustomers(fields);
			List<CostCentersCustomersDetails> list = new ArrayList<CostCentersCustomersDetails>();
			for (CostCentersCustomers costCentersCustomers : all) {
				list.add(new CostCentersCustomersDetails().toCostCentersCustomersDetails(costCentersCustomers, fields));
			}
			return new CostCentersCustomersPageEvent(list);
		}public CostCentersCustomersPageEvent requestAllBySubDescription(EmbeddedField... fields) throws Exception {
			List<CostCentersCustomers> all = costCentersCustomersCustomRepo.findAllActiveBySubDescription(fields);
			List<CostCentersCustomersDetails> list = new ArrayList<CostCentersCustomersDetails>();
			for (CostCentersCustomers costCentersCustomers : all) {
				list.add(new CostCentersCustomersDetails().toCostCentersCustomersDetails(costCentersCustomers, fields));
			}
			return new CostCentersCustomersPageEvent(list);
		}public CostCentersCustomersPageEvent requestAllByContact(EmbeddedField... fields) throws Exception {
			List<CostCentersCustomers> all = costCentersCustomersCustomRepo.findAllActiveByContact(fields);
			List<CostCentersCustomersDetails> list = new ArrayList<CostCentersCustomersDetails>();
			for (CostCentersCustomers costCentersCustomers : all) {
				list.add(new CostCentersCustomersDetails().toCostCentersCustomersDetails(costCentersCustomers, fields));
			}
			return new CostCentersCustomersPageEvent(list);
		}public CostCentersCustomersPageEvent requestAllByAreaDepartment(EmbeddedField... fields) throws Exception {
			List<CostCentersCustomers> all = costCentersCustomersCustomRepo.findAllActiveByAreaDepartment(fields);
			List<CostCentersCustomersDetails> list = new ArrayList<CostCentersCustomersDetails>();
			for (CostCentersCustomers costCentersCustomers : all) {
				list.add(new CostCentersCustomersDetails().toCostCentersCustomersDetails(costCentersCustomers, fields));
			}
			return new CostCentersCustomersPageEvent(list);
		}public CostCentersCustomersPageEvent requestAllByAddress(EmbeddedField... fields) throws Exception {
			List<CostCentersCustomers> all = costCentersCustomersCustomRepo.findAllActiveByAddress(fields);
			List<CostCentersCustomersDetails> list = new ArrayList<CostCentersCustomersDetails>();
			for (CostCentersCustomers costCentersCustomers : all) {
				list.add(new CostCentersCustomersDetails().toCostCentersCustomersDetails(costCentersCustomers, fields));
			}
			return new CostCentersCustomersPageEvent(list);
		}public CostCentersCustomersPageEvent requestAllByCity(EmbeddedField... fields) throws Exception {
			List<CostCentersCustomers> all = costCentersCustomersCustomRepo.findAllActiveByCity(fields);
			List<CostCentersCustomersDetails> list = new ArrayList<CostCentersCustomersDetails>();
			for (CostCentersCustomers costCentersCustomers : all) {
				list.add(new CostCentersCustomersDetails().toCostCentersCustomersDetails(costCentersCustomers, fields));
			}
			return new CostCentersCustomersPageEvent(list);
		}public CostCentersCustomersPageEvent requestAllByPhone(EmbeddedField... fields) throws Exception {
			List<CostCentersCustomers> all = costCentersCustomersCustomRepo.findAllActiveByPhone(fields);
			List<CostCentersCustomersDetails> list = new ArrayList<CostCentersCustomersDetails>();
			for (CostCentersCustomers costCentersCustomers : all) {
				list.add(new CostCentersCustomersDetails().toCostCentersCustomersDetails(costCentersCustomers, fields));
			}
			return new CostCentersCustomersPageEvent(list);
		}public CostCentersCustomersPageEvent requestAllByState(EmbeddedField... fields) throws Exception {
			List<CostCentersCustomers> all = costCentersCustomersCustomRepo.findAllActiveByState(fields);
			List<CostCentersCustomersDetails> list = new ArrayList<CostCentersCustomersDetails>();
			for (CostCentersCustomers costCentersCustomers : all) {
				list.add(new CostCentersCustomersDetails().toCostCentersCustomersDetails(costCentersCustomers, fields));
			}
			return new CostCentersCustomersPageEvent(list);
		}public CostCentersCustomersPageEvent requestAllByComments(EmbeddedField... fields) throws Exception {
			List<CostCentersCustomers> all = costCentersCustomersCustomRepo.findAllActiveByComments(fields);
			List<CostCentersCustomersDetails> list = new ArrayList<CostCentersCustomersDetails>();
			for (CostCentersCustomers costCentersCustomers : all) {
				list.add(new CostCentersCustomersDetails().toCostCentersCustomersDetails(costCentersCustomers, fields));
			}
			return new CostCentersCustomersPageEvent(list);
		}
	

	public CostCentersCustomersPageEvent requestCostCentersCustomersFilterPage(RequestCostCentersCustomersPageEvent event) throws Exception {
		Page<CostCentersCustomers> page = costCentersCustomersCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new CostCentersCustomersPageEvent(page, costCentersCustomersCustomRepo.getTotalCount());
	}
	
	public CostCentersCustomersPageEvent requestCostCentersCustomersFilter(
			RequestCostCentersCustomersPageEvent event) throws Exception {
		List<CostCentersCustomers> all = costCentersCustomersCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		CostCentersCustomersPageEvent pageEvent = new CostCentersCustomersPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}