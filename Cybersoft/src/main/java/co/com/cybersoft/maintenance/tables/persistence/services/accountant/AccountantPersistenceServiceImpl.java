package co.com.cybersoft.maintenance.tables.persistence.services.accountant;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.AccountantDetails;
import co.com.cybersoft.maintenance.tables.events.accountant.CreateAccountantEvent;
import co.com.cybersoft.maintenance.tables.events.accountant.AccountantDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.accountant.AccountantPageEvent;
import co.com.cybersoft.maintenance.tables.events.accountant.AccountantModificationEvent;
import co.com.cybersoft.maintenance.tables.events.accountant.RequestAccountantDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.accountant.RequestAccountantPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Accountant;
import co.com.cybersoft.maintenance.tables.persistence.repository.accountant.AccountantRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.accountant.AccountantCustomRepo;
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
public class AccountantPersistenceServiceImpl implements AccountantPersistenceService{

	private final AccountantRepository accountantRepository;
	
	private final AccountantCustomRepo accountantCustomRepo;
	
	
	public AccountantPersistenceServiceImpl(final AccountantRepository accountantRepository, final AccountantCustomRepo accountantCustomRepo) {
		this.accountantRepository=accountantRepository;
		this.accountantCustomRepo=accountantCustomRepo;
	}
	
	public AccountantDetailsEvent createAccountant(CreateAccountantEvent event) throws Exception{
		Accountant accountant = new Accountant().fromAccountantDetails(event.getAccountantDetails());
		accountant = accountantRepository.save(accountant);
		accountant = accountantRepository.findOne(accountant.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",accountant.getId());
		return new AccountantDetailsEvent(new AccountantDetails().toAccountantDetails(accountant));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"accountant", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public AccountantPageEvent requestAccountantPage(RequestAccountantPageEvent event) throws Exception {
		Page<Accountant> accountants = accountantRepository.findAll(event.getPageable());
		return new AccountantPageEvent(accountants);
	}

	public AccountantDetailsEvent requestAccountantDetails(RequestAccountantDetailsEvent event) throws Exception {
		AccountantDetails accountantDetails=null;
		if (event.getId()!=null){
			Accountant accountant = accountantRepository.findOne(event.getId());
			if (accountant!=null)
				accountantDetails = new AccountantDetails().toAccountantDetails(accountant);
		}
		return new AccountantDetailsEvent(accountantDetails);
		
	}

	public AccountantDetailsEvent modifyAccountant(AccountantModificationEvent event) throws Exception {
		Accountant accountant = new Accountant().fromAccountantDetails(event.getAccountantDetails());
		accountant = accountantRepository.save(accountant);
		accountant = accountantRepository.findOne(accountant.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",accountant.getId());
		return new AccountantDetailsEvent(new AccountantDetails().toAccountantDetails(accountant));
	}
	
		public AccountantDetailsEvent getOnlyRecord() throws Exception {
			Iterable<Accountant> all = accountantRepository.findAll();
			AccountantDetails single = new AccountantDetails();
			for (Accountant accountant : all) {
				single=new AccountantDetails().toAccountantDetails(accountant);
				break;
			}
			return new AccountantDetailsEvent(single);
		}
	
	public AccountantPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<Accountant> all = accountantCustomRepo.findAllActiveByCompany(fields);
			List<AccountantDetails> list = new ArrayList<AccountantDetails>();
			for (Accountant accountant : all) {
				list.add(new AccountantDetails().toAccountantDetails(accountant, fields));
			}
			return new AccountantPageEvent(list);
		}public AccountantPageEvent requestAllByNameAccountant(EmbeddedField... fields) throws Exception {
			List<Accountant> all = accountantCustomRepo.findAllActiveByNameAccountant(fields);
			List<AccountantDetails> list = new ArrayList<AccountantDetails>();
			for (Accountant accountant : all) {
				list.add(new AccountantDetails().toAccountantDetails(accountant, fields));
			}
			return new AccountantPageEvent(list);
		}
	

	public AccountantPageEvent requestAccountantFilterPage(RequestAccountantPageEvent event) throws Exception {
		Page<Accountant> page = accountantCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new AccountantPageEvent(page, accountantCustomRepo.getTotalCount());
	}
	
	public AccountantPageEvent requestAccountantFilter(
			RequestAccountantPageEvent event) throws Exception {
		List<Accountant> all = accountantCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		AccountantPageEvent pageEvent = new AccountantPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}