package co.com.cybersoft.core.services.active;

import co.com.cybersoft.events.active.CreateActiveEvent;
import co.com.cybersoft.events.active.ActiveDetailsEvent;
import co.com.cybersoft.events.active.ActivePageEvent;
import co.com.cybersoft.events.active.ActiveModificationEvent;
import co.com.cybersoft.events.active.RequestActiveDetailsEvent;
import co.com.cybersoft.events.active.RequestActivePageEvent;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface ActiveService {
	ActiveDetailsEvent createActive(CreateActiveEvent event ) throws Exception;
	
	ActivePageEvent requestActivePage(RequestActivePageEvent event) throws Exception;

	ActiveDetailsEvent requestActiveDetails(RequestActiveDetailsEvent event ) throws Exception;

	ActiveDetailsEvent modifyActive(ActiveModificationEvent event) throws Exception;
	
	ActivePageEvent requestAll() throws Exception;
	
	ActivePageEvent requestByCodePrefix(String code) throws Exception;
	
	ActivePageEvent requestByContainingDescription(String description) throws Exception;
	
}