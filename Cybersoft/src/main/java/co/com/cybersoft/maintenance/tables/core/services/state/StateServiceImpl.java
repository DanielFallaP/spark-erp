package co.com.cybersoft.maintenance.tables.core.services.state;

import co.com.cybersoft.maintenance.tables.events.state.CreateStateEvent;
import co.com.cybersoft.maintenance.tables.events.state.StateDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.state.StatePageEvent;
import co.com.cybersoft.maintenance.tables.events.state.StateModificationEvent;
import co.com.cybersoft.maintenance.tables.events.state.RequestStateDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.state.RequestStatePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.state.StatePersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class StateServiceImpl implements StateService{

	private final StatePersistenceService statePersistenceService;
	
	public StateServiceImpl(final StatePersistenceService statePersistenceService){
		this.statePersistenceService=statePersistenceService;
	}
	
	/**
	 */
	public StateDetailsEvent createState(CreateStateEvent event ) throws Exception{
		return statePersistenceService.createState(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public StatePageEvent requestStatePage(RequestStatePageEvent event) throws Exception{
		return statePersistenceService.requestStatePage(event);
	}

	public StateDetailsEvent requestStateDetails(RequestStateDetailsEvent event ) throws Exception{
		return statePersistenceService.requestStateDetails(event);
	}

	public StateDetailsEvent modifyState(StateModificationEvent event) throws Exception {
		return statePersistenceService.modifyState(event);
	}
	
	public StateDetailsEvent requestOnlyRecord() throws Exception {
		return statePersistenceService.getOnlyRecord();
	}
	
	public StatePageEvent requestStateFilterPage(RequestStatePageEvent event) throws Exception {
		return statePersistenceService.requestStateFilterPage(event);
	}
	
	public StatePageEvent requestStateFilter(RequestStatePageEvent event) throws Exception {
		return statePersistenceService.requestStateFilter(event);
	}
	

	public StatePageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return statePersistenceService.requestAllByCompany(fields);
	}public StatePageEvent requestAllByNameState(EmbeddedField... fields) throws Exception {
		return statePersistenceService.requestAllByNameState(fields);
	}
	
	
	
}