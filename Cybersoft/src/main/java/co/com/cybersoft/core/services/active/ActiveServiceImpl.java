package co.com.cybersoft.core.services.active;

import java.util.Date;

import co.com.cybersoft.events.active.CreateActiveEvent;
import co.com.cybersoft.events.active.ActiveDetailsEvent;
import co.com.cybersoft.events.active.ActivePageEvent;
import co.com.cybersoft.events.active.ActiveModificationEvent;
import co.com.cybersoft.events.active.RequestActiveDetailsEvent;
import co.com.cybersoft.events.active.RequestActivePageEvent;
import co.com.cybersoft.persistence.services.active.ActivePersistenceService;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ActiveServiceImpl implements ActiveService{

	private final ActivePersistenceService activePersistenceService;
	
	public ActiveServiceImpl(final ActivePersistenceService activePersistenceService){
		this.activePersistenceService=activePersistenceService;
	}
	
	/**
	 */
	@Override
	public ActiveDetailsEvent createActive(CreateActiveEvent event ) throws Exception{
		return activePersistenceService.createActive(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	@Override
	public ActivePageEvent requestActivePage(RequestActivePageEvent event) throws Exception{
		return activePersistenceService.requestActivePage(event);
	}

	@Override
	public ActiveDetailsEvent requestActiveDetails(RequestActiveDetailsEvent event ) throws Exception{
		return activePersistenceService.requestActiveDetails(event);
	}

	@Override
	public ActiveDetailsEvent modifyActive(ActiveModificationEvent event) throws Exception {
		return activePersistenceService.modifyActive(event);
	}
	
	@Override
	public ActivePageEvent requestAll() throws Exception {
		return activePersistenceService.requestAll();
	}

}