package co.com.cybersoft.maintenance.tables.persistence.services.state;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.StateDetails;
import co.com.cybersoft.maintenance.tables.events.state.CreateStateEvent;
import co.com.cybersoft.maintenance.tables.events.state.StateDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.state.StatePageEvent;
import co.com.cybersoft.maintenance.tables.events.state.StateModificationEvent;
import co.com.cybersoft.maintenance.tables.events.state.RequestStateDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.state.RequestStatePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.State;
import co.com.cybersoft.maintenance.tables.persistence.repository.state.StateRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.state.StateCustomRepo;
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
public class StatePersistenceServiceImpl implements StatePersistenceService{

	private final StateRepository stateRepository;
	
	private final StateCustomRepo stateCustomRepo;
	
	
	public StatePersistenceServiceImpl(final StateRepository stateRepository, final StateCustomRepo stateCustomRepo) {
		this.stateRepository=stateRepository;
		this.stateCustomRepo=stateCustomRepo;
	}
	
	public StateDetailsEvent createState(CreateStateEvent event) throws Exception{
		State state = new State().fromStateDetails(event.getStateDetails());
		state = stateRepository.save(state);
		state = stateRepository.findOne(state.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",state.getId());
		return new StateDetailsEvent(new StateDetails().toStateDetails(state));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"state", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public StatePageEvent requestStatePage(RequestStatePageEvent event) throws Exception {
		Page<State> states = stateRepository.findAll(event.getPageable());
		return new StatePageEvent(states);
	}

	public StateDetailsEvent requestStateDetails(RequestStateDetailsEvent event) throws Exception {
		StateDetails stateDetails=null;
		if (event.getId()!=null){
			State state = stateRepository.findOne(event.getId());
			if (state!=null)
				stateDetails = new StateDetails().toStateDetails(state);
		}
		return new StateDetailsEvent(stateDetails);
		
	}

	public StateDetailsEvent modifyState(StateModificationEvent event) throws Exception {
		State state = new State().fromStateDetails(event.getStateDetails());
		state = stateRepository.save(state);
		state = stateRepository.findOne(state.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",state.getId());
		return new StateDetailsEvent(new StateDetails().toStateDetails(state));
	}
	
		public StateDetailsEvent getOnlyRecord() throws Exception {
			Iterable<State> all = stateRepository.findAll();
			StateDetails single = new StateDetails();
			for (State state : all) {
				single=new StateDetails().toStateDetails(state);
				break;
			}
			return new StateDetailsEvent(single);
		}
	
	public StatePageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<State> all = stateCustomRepo.findAllActiveByCompany(fields);
			List<StateDetails> list = new ArrayList<StateDetails>();
			for (State state : all) {
				list.add(new StateDetails().toStateDetails(state, fields));
			}
			return new StatePageEvent(list);
		}public StatePageEvent requestAllByNameState(EmbeddedField... fields) throws Exception {
			List<State> all = stateCustomRepo.findAllActiveByNameState(fields);
			List<StateDetails> list = new ArrayList<StateDetails>();
			for (State state : all) {
				list.add(new StateDetails().toStateDetails(state, fields));
			}
			return new StatePageEvent(list);
		}
	

	public StatePageEvent requestStateFilterPage(RequestStatePageEvent event) throws Exception {
		Page<State> page = stateCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new StatePageEvent(page, stateCustomRepo.getTotalCount());
	}
	
	public StatePageEvent requestStateFilter(
			RequestStatePageEvent event) throws Exception {
		List<State> all = stateCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		StatePageEvent pageEvent = new StatePageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}