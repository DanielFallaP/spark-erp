package co.com.cybersoft.maintenance.tables.core.services.state;

import co.com.cybersoft.maintenance.tables.events.state.CreateStateEvent;
import co.com.cybersoft.maintenance.tables.events.state.StateDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.state.StatePageEvent;
import co.com.cybersoft.maintenance.tables.events.state.StateModificationEvent;
import co.com.cybersoft.maintenance.tables.events.state.RequestStateDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.state.RequestStatePageEvent;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface StateService {
	StateDetailsEvent requestOnlyRecord() throws Exception;

	StateDetailsEvent createState(CreateStateEvent event ) throws Exception;
	
	StatePageEvent requestStatePage(RequestStatePageEvent event) throws Exception;

	StateDetailsEvent requestStateDetails(RequestStateDetailsEvent event ) throws Exception;

	StateDetailsEvent modifyState(StateModificationEvent event) throws Exception;
	
	StatePageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	StatePageEvent requestAllByNameState(EmbeddedField... fields) throws Exception;

	
	
	StatePageEvent requestStateFilterPage(RequestStatePageEvent event) throws Exception;

	StatePageEvent requestStateFilter(RequestStatePageEvent event) throws Exception;
	
}