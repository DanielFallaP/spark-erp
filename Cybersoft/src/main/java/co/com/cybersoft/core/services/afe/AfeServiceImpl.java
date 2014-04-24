package co.com.cybersoft.core.services.afe;

import co.com.cybersoft.events.afe.CreateAfeEvent;
import co.com.cybersoft.events.afe.AfeDetailsEvent;
import co.com.cybersoft.events.afe.AfePageEvent;
import co.com.cybersoft.events.afe.AfeModificationEvent;
import co.com.cybersoft.events.afe.RequestAfeDetailsEvent;
import co.com.cybersoft.events.afe.RequestAfePageEvent;
import co.com.cybersoft.persistence.services.afe.AfePersistenceService;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class AfeServiceImpl implements AfeService{

	private final AfePersistenceService afePersistenceService;
	
	public AfeServiceImpl(final AfePersistenceService afePersistenceService){
		this.afePersistenceService=afePersistenceService;
	}
	
	/**
	 */
	@Override
	public AfeDetailsEvent createAfe(CreateAfeEvent event ) throws Exception{
		return afePersistenceService.createAfe(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	@Override
	public AfePageEvent requestAfePage(RequestAfePageEvent event) throws Exception{
		return afePersistenceService.requestAfePage(event);
	}

	@Override
	public AfeDetailsEvent requestAfeDetails(RequestAfeDetailsEvent event ) throws Exception{
		return afePersistenceService.requestAfeDetails(event);
	}

	@Override
	public AfeDetailsEvent modifyAfe(AfeModificationEvent event) throws Exception {
		return afePersistenceService.modifyAfe(event);
	}
	
	@Override
	public AfePageEvent requestAll() throws Exception {
		return afePersistenceService.requestAll();
	}
	
	@Override
	public AfePageEvent requestByCodePrefix(String codePrefix) throws Exception {
		return afePersistenceService.requestByCodePrefix(codePrefix);
	}

	@Override
	public AfePageEvent requestByContainingDescription(String description) throws Exception {
		return afePersistenceService.requestByContainingDescription(description);
	}
	
}