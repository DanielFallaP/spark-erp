package co.com.cybersoft.purchase.tables.core.services.continent;

import co.com.cybersoft.purchase.tables.events.continent.CreateContinentEvent;
import co.com.cybersoft.purchase.tables.events.continent.ContinentDetailsEvent;
import co.com.cybersoft.purchase.tables.events.continent.ContinentPageEvent;
import co.com.cybersoft.purchase.tables.events.continent.ContinentModificationEvent;
import co.com.cybersoft.purchase.tables.events.continent.RequestContinentDetailsEvent;
import co.com.cybersoft.purchase.tables.events.continent.RequestContinentPageEvent;
import co.com.cybersoft.purchase.tables.persistence.services.continent.ContinentPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ContinentServiceImpl implements ContinentService{

	private final ContinentPersistenceService continentPersistenceService;
	
	public ContinentServiceImpl(final ContinentPersistenceService continentPersistenceService){
		this.continentPersistenceService=continentPersistenceService;
	}
	
	/**
	 */
	public ContinentDetailsEvent createContinent(CreateContinentEvent event ) throws Exception{
		return continentPersistenceService.createContinent(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public ContinentPageEvent requestContinentPage(RequestContinentPageEvent event) throws Exception{
		return continentPersistenceService.requestContinentPage(event);
	}

	public ContinentDetailsEvent requestContinentDetails(RequestContinentDetailsEvent event ) throws Exception{
		return continentPersistenceService.requestContinentDetails(event);
	}

	public ContinentDetailsEvent modifyContinent(ContinentModificationEvent event) throws Exception {
		return continentPersistenceService.modifyContinent(event);
	}
	
	public ContinentDetailsEvent requestOnlyRecord() throws Exception {
		return continentPersistenceService.getOnlyRecord();
	}
	
	public ContinentPageEvent requestContinentFilterPage(RequestContinentPageEvent event) throws Exception {
		return continentPersistenceService.requestContinentFilterPage(event);
	}
	
	public ContinentPageEvent requestContinentFilter(RequestContinentPageEvent event) throws Exception {
		return continentPersistenceService.requestContinentFilter(event);
	}
	

	public ContinentPageEvent requestAllByContinent(EmbeddedField... fields) throws Exception {
		return continentPersistenceService.requestAllByContinent(fields);
	}
	
	public ContinentPageEvent requestByContainingContinent(String continent) throws Exception {
				return continentPersistenceService.requestByContainingContinent(continent);
			}
	
	
}