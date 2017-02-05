package co.com.cybersoft.maintenance.tables.core.services.stateCostCenters;

import co.com.cybersoft.maintenance.tables.events.stateCostCenters.CreateStateCostCentersEvent;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.StateCostCentersDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.StateCostCentersPageEvent;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.StateCostCentersModificationEvent;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.RequestStateCostCentersDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.RequestStateCostCentersPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.stateCostCenters.StateCostCentersPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class StateCostCentersServiceImpl implements StateCostCentersService{

	private final StateCostCentersPersistenceService stateCostCentersPersistenceService;
	
	public StateCostCentersServiceImpl(final StateCostCentersPersistenceService stateCostCentersPersistenceService){
		this.stateCostCentersPersistenceService=stateCostCentersPersistenceService;
	}
	
	/**
	 */
	public StateCostCentersDetailsEvent createStateCostCenters(CreateStateCostCentersEvent event ) throws Exception{
		return stateCostCentersPersistenceService.createStateCostCenters(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public StateCostCentersPageEvent requestStateCostCentersPage(RequestStateCostCentersPageEvent event) throws Exception{
		return stateCostCentersPersistenceService.requestStateCostCentersPage(event);
	}

	public StateCostCentersDetailsEvent requestStateCostCentersDetails(RequestStateCostCentersDetailsEvent event ) throws Exception{
		return stateCostCentersPersistenceService.requestStateCostCentersDetails(event);
	}

	public StateCostCentersDetailsEvent modifyStateCostCenters(StateCostCentersModificationEvent event) throws Exception {
		return stateCostCentersPersistenceService.modifyStateCostCenters(event);
	}
	
	public StateCostCentersDetailsEvent requestOnlyRecord() throws Exception {
		return stateCostCentersPersistenceService.getOnlyRecord();
	}
	
	public StateCostCentersPageEvent requestStateCostCentersFilterPage(RequestStateCostCentersPageEvent event) throws Exception {
		return stateCostCentersPersistenceService.requestStateCostCentersFilterPage(event);
	}
	
	public StateCostCentersPageEvent requestStateCostCentersFilter(RequestStateCostCentersPageEvent event) throws Exception {
		return stateCostCentersPersistenceService.requestStateCostCentersFilter(event);
	}
	

	public StateCostCentersPageEvent requestAllByStateCostCenters(EmbeddedField... fields) throws Exception {
		return stateCostCentersPersistenceService.requestAllByStateCostCenters(fields);
	}
	
	
	
}