package co.com.cybersoft.purchase.tables.persistence.services.users;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.purchase.tables.core.domain.UsersDetails;
import co.com.cybersoft.purchase.tables.events.users.CreateUsersEvent;
import co.com.cybersoft.purchase.tables.events.users.UsersDetailsEvent;
import co.com.cybersoft.purchase.tables.events.users.UsersPageEvent;
import co.com.cybersoft.purchase.tables.events.users.UsersModificationEvent;
import co.com.cybersoft.purchase.tables.events.users.RequestUsersDetailsEvent;
import co.com.cybersoft.purchase.tables.events.users.RequestUsersPageEvent;
import co.com.cybersoft.purchase.tables.persistence.domain.Users;
import co.com.cybersoft.purchase.tables.persistence.repository.users.UsersRepository;
import co.com.cybersoft.purchase.tables.persistence.repository.users.UsersCustomRepo;
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
public class UsersPersistenceServiceImpl implements UsersPersistenceService{

	private final UsersRepository usersRepository;
	
	private final UsersCustomRepo usersCustomRepo;
	
	
	public UsersPersistenceServiceImpl(final UsersRepository usersRepository, final UsersCustomRepo usersCustomRepo) {
		this.usersRepository=usersRepository;
		this.usersCustomRepo=usersCustomRepo;
	}
	
	public UsersDetailsEvent createUsers(CreateUsersEvent event) throws Exception{
		Users users = new Users().fromUsersDetails(event.getUsersDetails());
		users = usersRepository.save(users);
		users = usersRepository.findOne(users.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",users.getId());
		return new UsersDetailsEvent(new UsersDetails().toUsersDetails(users));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"users", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public UsersPageEvent requestUsersPage(RequestUsersPageEvent event) throws Exception {
		Page<Users> userss = usersRepository.findAll(event.getPageable());
		return new UsersPageEvent(userss);
	}

	public UsersDetailsEvent requestUsersDetails(RequestUsersDetailsEvent event) throws Exception {
		UsersDetails usersDetails=null;
		if (event.getId()!=null){
			Users users = usersRepository.findOne(event.getId());
			if (users!=null)
				usersDetails = new UsersDetails().toUsersDetails(users);
		}
		return new UsersDetailsEvent(usersDetails);
		
	}

	public UsersDetailsEvent modifyUsers(UsersModificationEvent event) throws Exception {
		Users users = new Users().fromUsersDetails(event.getUsersDetails());
		users = usersRepository.save(users);
		users = usersRepository.findOne(users.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",users.getId());
		return new UsersDetailsEvent(new UsersDetails().toUsersDetails(users));
	}
	
		public UsersDetailsEvent getOnlyRecord() throws Exception {
			Iterable<Users> all = usersRepository.findAll();
			UsersDetails single = new UsersDetails();
			for (Users users : all) {
				single=new UsersDetails().toUsersDetails(users);
				break;
			}
			return new UsersDetailsEvent(single);
		}
	
	public UsersPageEvent requestAllByLogin(EmbeddedField... fields) throws Exception {
			List<Users> all = usersCustomRepo.findAllActiveByLogin(fields);
			List<UsersDetails> list = new ArrayList<UsersDetails>();
			for (Users users : all) {
				list.add(new UsersDetails().toUsersDetails(users, fields));
			}
			return new UsersPageEvent(list);
		}public UsersPageEvent requestAllByPassword(EmbeddedField... fields) throws Exception {
			List<Users> all = usersCustomRepo.findAllActiveByPassword(fields);
			List<UsersDetails> list = new ArrayList<UsersDetails>();
			for (Users users : all) {
				list.add(new UsersDetails().toUsersDetails(users, fields));
			}
			return new UsersPageEvent(list);
		}public UsersPageEvent requestAllByRole(EmbeddedField... fields) throws Exception {
			List<Users> all = usersCustomRepo.findAllActiveByRole(fields);
			List<UsersDetails> list = new ArrayList<UsersDetails>();
			for (Users users : all) {
				list.add(new UsersDetails().toUsersDetails(users, fields));
			}
			return new UsersPageEvent(list);
		}public UsersPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<Users> all = usersCustomRepo.findAllActiveByCompany(fields);
			List<UsersDetails> list = new ArrayList<UsersDetails>();
			for (Users users : all) {
				list.add(new UsersDetails().toUsersDetails(users, fields));
			}
			return new UsersPageEvent(list);
		}
	

	public UsersPageEvent requestUsersFilterPage(RequestUsersPageEvent event) throws Exception {
		Page<Users> page = usersCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new UsersPageEvent(page, usersCustomRepo.getTotalCount());
	}
	
	public UsersPageEvent requestUsersFilter(
			RequestUsersPageEvent event) throws Exception {
		List<Users> all = usersCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		UsersPageEvent pageEvent = new UsersPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}