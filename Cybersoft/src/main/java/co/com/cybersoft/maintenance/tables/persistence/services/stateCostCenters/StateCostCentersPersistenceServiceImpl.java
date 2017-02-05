package co.com.cybersoft.maintenance.tables.persistence.services.stateCostCenters;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.StateCostCentersDetails;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.CreateStateCostCentersEvent;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.StateCostCentersDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.StateCostCentersPageEvent;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.StateCostCentersModificationEvent;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.RequestStateCostCentersDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.RequestStateCostCentersPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.StateCostCenters;
import co.com.cybersoft.maintenance.tables.persistence.repository.stateCostCenters.StateCostCentersRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.stateCostCenters.StateCostCentersCustomRepo;
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
public class StateCostCentersPersistenceServiceImpl implements StateCostCentersPersistenceService{

	private final StateCostCentersRepository stateCostCentersRepository;
	
	private final StateCostCentersCustomRepo stateCostCentersCustomRepo;
	
	
	public StateCostCentersPersistenceServiceImpl(final StateCostCentersRepository stateCostCentersRepository, final StateCostCentersCustomRepo stateCostCentersCustomRepo) {
		this.stateCostCentersRepository=stateCostCentersRepository;
		this.stateCostCentersCustomRepo=stateCostCentersCustomRepo;
	}
	
	public StateCostCentersDetailsEvent createStateCostCenters(CreateStateCostCentersEvent event) throws Exception{
		StateCostCenters stateCostCenters = new StateCostCenters().fromStateCostCentersDetails(event.getStateCostCentersDetails());
		stateCostCenters = stateCostCentersRepository.save(stateCostCenters);
		stateCostCenters = stateCostCentersRepository.findOne(stateCostCenters.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",stateCostCenters.getId());
		return new StateCostCentersDetailsEvent(new StateCostCentersDetails().toStateCostCentersDetails(stateCostCenters));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"stateCostCenters", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public StateCostCentersPageEvent requestStateCostCentersPage(RequestStateCostCentersPageEvent event) throws Exception {
		Page<StateCostCenters> stateCostCenterss = stateCostCentersRepository.findAll(event.getPageable());
		return new StateCostCentersPageEvent(stateCostCenterss);
	}

	public StateCostCentersDetailsEvent requestStateCostCentersDetails(RequestStateCostCentersDetailsEvent event) throws Exception {
		StateCostCentersDetails stateCostCentersDetails=null;
		if (event.getId()!=null){
			StateCostCenters stateCostCenters = stateCostCentersRepository.findOne(event.getId());
			if (stateCostCenters!=null)
				stateCostCentersDetails = new StateCostCentersDetails().toStateCostCentersDetails(stateCostCenters);
		}
		return new StateCostCentersDetailsEvent(stateCostCentersDetails);
		
	}

	public StateCostCentersDetailsEvent modifyStateCostCenters(StateCostCentersModificationEvent event) throws Exception {
		StateCostCenters stateCostCenters = new StateCostCenters().fromStateCostCentersDetails(event.getStateCostCentersDetails());
		stateCostCenters = stateCostCentersRepository.save(stateCostCenters);
		stateCostCenters = stateCostCentersRepository.findOne(stateCostCenters.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",stateCostCenters.getId());
		return new StateCostCentersDetailsEvent(new StateCostCentersDetails().toStateCostCentersDetails(stateCostCenters));
	}
	
		public StateCostCentersDetailsEvent getOnlyRecord() throws Exception {
			Iterable<StateCostCenters> all = stateCostCentersRepository.findAll();
			StateCostCentersDetails single = new StateCostCentersDetails();
			for (StateCostCenters stateCostCenters : all) {
				single=new StateCostCentersDetails().toStateCostCentersDetails(stateCostCenters);
				break;
			}
			return new StateCostCentersDetailsEvent(single);
		}
	
	public StateCostCentersPageEvent requestAllByStateCostCenters(EmbeddedField... fields) throws Exception {
			List<StateCostCenters> all = stateCostCentersCustomRepo.findAllActiveByStateCostCenters(fields);
			List<StateCostCentersDetails> list = new ArrayList<StateCostCentersDetails>();
			for (StateCostCenters stateCostCenters : all) {
				list.add(new StateCostCentersDetails().toStateCostCentersDetails(stateCostCenters, fields));
			}
			return new StateCostCentersPageEvent(list);
		}
	

	public StateCostCentersPageEvent requestStateCostCentersFilterPage(RequestStateCostCentersPageEvent event) throws Exception {
		Page<StateCostCenters> page = stateCostCentersCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new StateCostCentersPageEvent(page, stateCostCentersCustomRepo.getTotalCount());
	}
	
	public StateCostCentersPageEvent requestStateCostCentersFilter(
			RequestStateCostCentersPageEvent event) throws Exception {
		List<StateCostCenters> all = stateCostCentersCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		StateCostCentersPageEvent pageEvent = new StateCostCentersPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}