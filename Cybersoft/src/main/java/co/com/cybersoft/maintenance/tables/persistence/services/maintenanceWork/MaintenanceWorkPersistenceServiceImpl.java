package co.com.cybersoft.maintenance.tables.persistence.services.maintenanceWork;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.MaintenanceWorkDetails;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.CreateMaintenanceWorkEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.MaintenanceWorkDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.MaintenanceWorkPageEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.MaintenanceWorkModificationEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.RequestMaintenanceWorkDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.RequestMaintenanceWorkPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.MaintenanceWork;
import co.com.cybersoft.maintenance.tables.persistence.repository.maintenanceWork.MaintenanceWorkRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.maintenanceWork.MaintenanceWorkCustomRepo;
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
public class MaintenanceWorkPersistenceServiceImpl implements MaintenanceWorkPersistenceService{

	private final MaintenanceWorkRepository maintenanceWorkRepository;
	
	private final MaintenanceWorkCustomRepo maintenanceWorkCustomRepo;
	
	
	public MaintenanceWorkPersistenceServiceImpl(final MaintenanceWorkRepository maintenanceWorkRepository, final MaintenanceWorkCustomRepo maintenanceWorkCustomRepo) {
		this.maintenanceWorkRepository=maintenanceWorkRepository;
		this.maintenanceWorkCustomRepo=maintenanceWorkCustomRepo;
	}
	
	public MaintenanceWorkDetailsEvent createMaintenanceWork(CreateMaintenanceWorkEvent event) throws Exception{
		MaintenanceWork maintenanceWork = new MaintenanceWork().fromMaintenanceWorkDetails(event.getMaintenanceWorkDetails());
		maintenanceWork = maintenanceWorkRepository.save(maintenanceWork);
		maintenanceWork = maintenanceWorkRepository.findOne(maintenanceWork.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",maintenanceWork.getId());
		return new MaintenanceWorkDetailsEvent(new MaintenanceWorkDetails().toMaintenanceWorkDetails(maintenanceWork));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"maintenanceWork", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public MaintenanceWorkPageEvent requestMaintenanceWorkPage(RequestMaintenanceWorkPageEvent event) throws Exception {
		Page<MaintenanceWork> maintenanceWorks = maintenanceWorkRepository.findAll(event.getPageable());
		return new MaintenanceWorkPageEvent(maintenanceWorks);
	}

	public MaintenanceWorkDetailsEvent requestMaintenanceWorkDetails(RequestMaintenanceWorkDetailsEvent event) throws Exception {
		MaintenanceWorkDetails maintenanceWorkDetails=null;
		if (event.getId()!=null){
			MaintenanceWork maintenanceWork = maintenanceWorkRepository.findOne(event.getId());
			if (maintenanceWork!=null)
				maintenanceWorkDetails = new MaintenanceWorkDetails().toMaintenanceWorkDetails(maintenanceWork);
		}
		return new MaintenanceWorkDetailsEvent(maintenanceWorkDetails);
		
	}

	public MaintenanceWorkDetailsEvent modifyMaintenanceWork(MaintenanceWorkModificationEvent event) throws Exception {
		MaintenanceWork maintenanceWork = new MaintenanceWork().fromMaintenanceWorkDetails(event.getMaintenanceWorkDetails());
		maintenanceWork = maintenanceWorkRepository.save(maintenanceWork);
		maintenanceWork = maintenanceWorkRepository.findOne(maintenanceWork.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",maintenanceWork.getId());
		return new MaintenanceWorkDetailsEvent(new MaintenanceWorkDetails().toMaintenanceWorkDetails(maintenanceWork));
	}
	
		public MaintenanceWorkDetailsEvent getOnlyRecord() throws Exception {
			Iterable<MaintenanceWork> all = maintenanceWorkRepository.findAll();
			MaintenanceWorkDetails single = new MaintenanceWorkDetails();
			for (MaintenanceWork maintenanceWork : all) {
				single=new MaintenanceWorkDetails().toMaintenanceWorkDetails(maintenanceWork);
				break;
			}
			return new MaintenanceWorkDetailsEvent(single);
		}
	
	public MaintenanceWorkPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<MaintenanceWork> all = maintenanceWorkCustomRepo.findAllActiveByCompany(fields);
			List<MaintenanceWorkDetails> list = new ArrayList<MaintenanceWorkDetails>();
			for (MaintenanceWork maintenanceWork : all) {
				list.add(new MaintenanceWorkDetails().toMaintenanceWorkDetails(maintenanceWork, fields));
			}
			return new MaintenanceWorkPageEvent(list);
		}public MaintenanceWorkPageEvent requestAllByNameMaintenanceWork(EmbeddedField... fields) throws Exception {
			List<MaintenanceWork> all = maintenanceWorkCustomRepo.findAllActiveByNameMaintenanceWork(fields);
			List<MaintenanceWorkDetails> list = new ArrayList<MaintenanceWorkDetails>();
			for (MaintenanceWork maintenanceWork : all) {
				list.add(new MaintenanceWorkDetails().toMaintenanceWorkDetails(maintenanceWork, fields));
			}
			return new MaintenanceWorkPageEvent(list);
		}
	

	public MaintenanceWorkPageEvent requestMaintenanceWorkFilterPage(RequestMaintenanceWorkPageEvent event) throws Exception {
		Page<MaintenanceWork> page = maintenanceWorkCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new MaintenanceWorkPageEvent(page, maintenanceWorkCustomRepo.getTotalCount());
	}
	
	public MaintenanceWorkPageEvent requestMaintenanceWorkFilter(
			RequestMaintenanceWorkPageEvent event) throws Exception {
		List<MaintenanceWork> all = maintenanceWorkCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		MaintenanceWorkPageEvent pageEvent = new MaintenanceWorkPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}