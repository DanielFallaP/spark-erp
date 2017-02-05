package co.com.cybersoft.maintenance.tables.persistence.services.maintenanceActivity;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.MaintenanceActivityDetails;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.CreateMaintenanceActivityEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.MaintenanceActivityDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.MaintenanceActivityPageEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.MaintenanceActivityModificationEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.RequestMaintenanceActivityDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.RequestMaintenanceActivityPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.MaintenanceActivity;
import co.com.cybersoft.maintenance.tables.persistence.repository.maintenanceActivity.MaintenanceActivityRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.maintenanceActivity.MaintenanceActivityCustomRepo;
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
public class MaintenanceActivityPersistenceServiceImpl implements MaintenanceActivityPersistenceService{

	private final MaintenanceActivityRepository maintenanceActivityRepository;
	
	private final MaintenanceActivityCustomRepo maintenanceActivityCustomRepo;
	
	
	public MaintenanceActivityPersistenceServiceImpl(final MaintenanceActivityRepository maintenanceActivityRepository, final MaintenanceActivityCustomRepo maintenanceActivityCustomRepo) {
		this.maintenanceActivityRepository=maintenanceActivityRepository;
		this.maintenanceActivityCustomRepo=maintenanceActivityCustomRepo;
	}
	
	public MaintenanceActivityDetailsEvent createMaintenanceActivity(CreateMaintenanceActivityEvent event) throws Exception{
		MaintenanceActivity maintenanceActivity = new MaintenanceActivity().fromMaintenanceActivityDetails(event.getMaintenanceActivityDetails());
		maintenanceActivity = maintenanceActivityRepository.save(maintenanceActivity);
		maintenanceActivity = maintenanceActivityRepository.findOne(maintenanceActivity.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",maintenanceActivity.getId());
		return new MaintenanceActivityDetailsEvent(new MaintenanceActivityDetails().toMaintenanceActivityDetails(maintenanceActivity));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"maintenanceActivity", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public MaintenanceActivityPageEvent requestMaintenanceActivityPage(RequestMaintenanceActivityPageEvent event) throws Exception {
		Page<MaintenanceActivity> maintenanceActivitys = maintenanceActivityRepository.findAll(event.getPageable());
		return new MaintenanceActivityPageEvent(maintenanceActivitys);
	}

	public MaintenanceActivityDetailsEvent requestMaintenanceActivityDetails(RequestMaintenanceActivityDetailsEvent event) throws Exception {
		MaintenanceActivityDetails maintenanceActivityDetails=null;
		if (event.getId()!=null){
			MaintenanceActivity maintenanceActivity = maintenanceActivityRepository.findOne(event.getId());
			if (maintenanceActivity!=null)
				maintenanceActivityDetails = new MaintenanceActivityDetails().toMaintenanceActivityDetails(maintenanceActivity);
		}
		return new MaintenanceActivityDetailsEvent(maintenanceActivityDetails);
		
	}

	public MaintenanceActivityDetailsEvent modifyMaintenanceActivity(MaintenanceActivityModificationEvent event) throws Exception {
		MaintenanceActivity maintenanceActivity = new MaintenanceActivity().fromMaintenanceActivityDetails(event.getMaintenanceActivityDetails());
		maintenanceActivity = maintenanceActivityRepository.save(maintenanceActivity);
		maintenanceActivity = maintenanceActivityRepository.findOne(maintenanceActivity.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",maintenanceActivity.getId());
		return new MaintenanceActivityDetailsEvent(new MaintenanceActivityDetails().toMaintenanceActivityDetails(maintenanceActivity));
	}
	
		public MaintenanceActivityDetailsEvent getOnlyRecord() throws Exception {
			Iterable<MaintenanceActivity> all = maintenanceActivityRepository.findAll();
			MaintenanceActivityDetails single = new MaintenanceActivityDetails();
			for (MaintenanceActivity maintenanceActivity : all) {
				single=new MaintenanceActivityDetails().toMaintenanceActivityDetails(maintenanceActivity);
				break;
			}
			return new MaintenanceActivityDetailsEvent(single);
		}
	
	public MaintenanceActivityPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<MaintenanceActivity> all = maintenanceActivityCustomRepo.findAllActiveByCompany(fields);
			List<MaintenanceActivityDetails> list = new ArrayList<MaintenanceActivityDetails>();
			for (MaintenanceActivity maintenanceActivity : all) {
				list.add(new MaintenanceActivityDetails().toMaintenanceActivityDetails(maintenanceActivity, fields));
			}
			return new MaintenanceActivityPageEvent(list);
		}public MaintenanceActivityPageEvent requestAllByNameMaintenanceActivity(EmbeddedField... fields) throws Exception {
			List<MaintenanceActivity> all = maintenanceActivityCustomRepo.findAllActiveByNameMaintenanceActivity(fields);
			List<MaintenanceActivityDetails> list = new ArrayList<MaintenanceActivityDetails>();
			for (MaintenanceActivity maintenanceActivity : all) {
				list.add(new MaintenanceActivityDetails().toMaintenanceActivityDetails(maintenanceActivity, fields));
			}
			return new MaintenanceActivityPageEvent(list);
		}public MaintenanceActivityPageEvent requestAllByDurationUnitStandard(EmbeddedField... fields) throws Exception {
			List<MaintenanceActivity> all = maintenanceActivityCustomRepo.findAllActiveByDurationUnitStandard(fields);
			List<MaintenanceActivityDetails> list = new ArrayList<MaintenanceActivityDetails>();
			for (MaintenanceActivity maintenanceActivity : all) {
				list.add(new MaintenanceActivityDetails().toMaintenanceActivityDetails(maintenanceActivity, fields));
			}
			return new MaintenanceActivityPageEvent(list);
		}
	

	public MaintenanceActivityPageEvent requestMaintenanceActivityFilterPage(RequestMaintenanceActivityPageEvent event) throws Exception {
		Page<MaintenanceActivity> page = maintenanceActivityCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new MaintenanceActivityPageEvent(page, maintenanceActivityCustomRepo.getTotalCount());
	}
	
	public MaintenanceActivityPageEvent requestMaintenanceActivityFilter(
			RequestMaintenanceActivityPageEvent event) throws Exception {
		List<MaintenanceActivity> all = maintenanceActivityCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		MaintenanceActivityPageEvent pageEvent = new MaintenanceActivityPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}