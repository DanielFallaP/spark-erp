package co.com.cybersoft.purchase.tables.persistence.services.customerTenancy;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.purchase.tables.core.domain.CustomerTenancyDetails;
import co.com.cybersoft.purchase.tables.events.customerTenancy.CreateCustomerTenancyEvent;
import co.com.cybersoft.purchase.tables.events.customerTenancy.CustomerTenancyDetailsEvent;
import co.com.cybersoft.purchase.tables.events.customerTenancy.CustomerTenancyPageEvent;
import co.com.cybersoft.purchase.tables.events.customerTenancy.CustomerTenancyModificationEvent;
import co.com.cybersoft.purchase.tables.events.customerTenancy.RequestCustomerTenancyDetailsEvent;
import co.com.cybersoft.purchase.tables.events.customerTenancy.RequestCustomerTenancyPageEvent;
import co.com.cybersoft.purchase.tables.persistence.domain.CustomerTenancy;
import co.com.cybersoft.purchase.tables.persistence.repository.customerTenancy.CustomerTenancyRepository;
import co.com.cybersoft.purchase.tables.persistence.repository.customerTenancy.CustomerTenancyCustomRepo;
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
public class CustomerTenancyPersistenceServiceImpl implements CustomerTenancyPersistenceService{

	private final CustomerTenancyRepository customerTenancyRepository;
	
	private final CustomerTenancyCustomRepo customerTenancyCustomRepo;
	
	
	public CustomerTenancyPersistenceServiceImpl(final CustomerTenancyRepository customerTenancyRepository, final CustomerTenancyCustomRepo customerTenancyCustomRepo) {
		this.customerTenancyRepository=customerTenancyRepository;
		this.customerTenancyCustomRepo=customerTenancyCustomRepo;
	}
	
	public CustomerTenancyDetailsEvent createCustomerTenancy(CreateCustomerTenancyEvent event) throws Exception{
		CustomerTenancy customerTenancy = new CustomerTenancy().fromCustomerTenancyDetails(event.getCustomerTenancyDetails());
		customerTenancy = customerTenancyRepository.save(customerTenancy);
		customerTenancy = customerTenancyRepository.findOne(customerTenancy.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",customerTenancy.getId());
		return new CustomerTenancyDetailsEvent(new CustomerTenancyDetails().toCustomerTenancyDetails(customerTenancy));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"customerTenancy", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public CustomerTenancyPageEvent requestCustomerTenancyPage(RequestCustomerTenancyPageEvent event) throws Exception {
		Page<CustomerTenancy> customerTenancys = customerTenancyRepository.findAll(event.getPageable());
		return new CustomerTenancyPageEvent(customerTenancys);
	}

	public CustomerTenancyDetailsEvent requestCustomerTenancyDetails(RequestCustomerTenancyDetailsEvent event) throws Exception {
		CustomerTenancyDetails customerTenancyDetails=null;
		if (event.getId()!=null){
			CustomerTenancy customerTenancy = customerTenancyRepository.findOne(event.getId());
			if (customerTenancy!=null)
				customerTenancyDetails = new CustomerTenancyDetails().toCustomerTenancyDetails(customerTenancy);
		}
		return new CustomerTenancyDetailsEvent(customerTenancyDetails);
		
	}

	public CustomerTenancyDetailsEvent modifyCustomerTenancy(CustomerTenancyModificationEvent event) throws Exception {
		CustomerTenancy customerTenancy = new CustomerTenancy().fromCustomerTenancyDetails(event.getCustomerTenancyDetails());
		customerTenancy = customerTenancyRepository.save(customerTenancy);
		customerTenancy = customerTenancyRepository.findOne(customerTenancy.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",customerTenancy.getId());
		return new CustomerTenancyDetailsEvent(new CustomerTenancyDetails().toCustomerTenancyDetails(customerTenancy));
	}
	
		public CustomerTenancyDetailsEvent getOnlyRecord() throws Exception {
			Iterable<CustomerTenancy> all = customerTenancyRepository.findAll();
			CustomerTenancyDetails single = new CustomerTenancyDetails();
			for (CustomerTenancy customerTenancy : all) {
				single=new CustomerTenancyDetails().toCustomerTenancyDetails(customerTenancy);
				break;
			}
			return new CustomerTenancyDetailsEvent(single);
		}
	
	public CustomerTenancyPageEvent requestAllBySoftwareTradeMark(EmbeddedField... fields) throws Exception {
			List<CustomerTenancy> all = customerTenancyCustomRepo.findAllActiveBySoftwareTradeMark(fields);
			List<CustomerTenancyDetails> list = new ArrayList<CustomerTenancyDetails>();
			for (CustomerTenancy customerTenancy : all) {
				list.add(new CustomerTenancyDetails().toCustomerTenancyDetails(customerTenancy, fields));
			}
			return new CustomerTenancyPageEvent(list);
		}public CustomerTenancyPageEvent requestAllBySoftwareVersion(EmbeddedField... fields) throws Exception {
			List<CustomerTenancy> all = customerTenancyCustomRepo.findAllActiveBySoftwareVersion(fields);
			List<CustomerTenancyDetails> list = new ArrayList<CustomerTenancyDetails>();
			for (CustomerTenancy customerTenancy : all) {
				list.add(new CustomerTenancyDetails().toCustomerTenancyDetails(customerTenancy, fields));
			}
			return new CustomerTenancyPageEvent(list);
		}public CustomerTenancyPageEvent requestAllBySoftwareSerialNo(EmbeddedField... fields) throws Exception {
			List<CustomerTenancy> all = customerTenancyCustomRepo.findAllActiveBySoftwareSerialNo(fields);
			List<CustomerTenancyDetails> list = new ArrayList<CustomerTenancyDetails>();
			for (CustomerTenancy customerTenancy : all) {
				list.add(new CustomerTenancyDetails().toCustomerTenancyDetails(customerTenancy, fields));
			}
			return new CustomerTenancyPageEvent(list);
		}public CustomerTenancyPageEvent requestAllByLocalCurrency(EmbeddedField... fields) throws Exception {
			List<CustomerTenancy> all = customerTenancyCustomRepo.findAllActiveByLocalCurrency(fields);
			List<CustomerTenancyDetails> list = new ArrayList<CustomerTenancyDetails>();
			for (CustomerTenancy customerTenancy : all) {
				list.add(new CustomerTenancyDetails().toCustomerTenancyDetails(customerTenancy, fields));
			}
			return new CustomerTenancyPageEvent(list);
		}public CustomerTenancyPageEvent requestAllByForeignCurrency(EmbeddedField... fields) throws Exception {
			List<CustomerTenancy> all = customerTenancyCustomRepo.findAllActiveByForeignCurrency(fields);
			List<CustomerTenancyDetails> list = new ArrayList<CustomerTenancyDetails>();
			for (CustomerTenancy customerTenancy : all) {
				list.add(new CustomerTenancyDetails().toCustomerTenancyDetails(customerTenancy, fields));
			}
			return new CustomerTenancyPageEvent(list);
		}
	

	public CustomerTenancyPageEvent requestCustomerTenancyFilterPage(RequestCustomerTenancyPageEvent event) throws Exception {
		Page<CustomerTenancy> page = customerTenancyCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new CustomerTenancyPageEvent(page, customerTenancyCustomRepo.getTotalCount());
	}
	
	public CustomerTenancyPageEvent requestCustomerTenancyFilter(
			RequestCustomerTenancyPageEvent event) throws Exception {
		List<CustomerTenancy> all = customerTenancyCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		CustomerTenancyPageEvent pageEvent = new CustomerTenancyPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}