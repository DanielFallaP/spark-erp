package co.com.cybersoft.maintenance.tables.persistence.services.stateCostCenters;

import co.com.cybersoft.maintenance.tables.events.stateCostCenters.CreateStateCostCentersEvent;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.StateCostCentersDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.StateCostCentersPageEvent;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.StateCostCentersModificationEvent;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.RequestStateCostCentersDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.RequestStateCostCentersPageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface StateCostCentersPersistenceService {

	StateCostCentersDetailsEvent createStateCostCenters(CreateStateCostCentersEvent event) throws Exception;

	StateCostCentersPageEvent requestStateCostCentersPage(RequestStateCostCentersPageEvent event) throws Exception;

	StateCostCentersDetailsEvent requestStateCostCentersDetails(RequestStateCostCentersDetailsEvent event) throws Exception;
	
	StateCostCentersDetailsEvent modifyStateCostCenters(StateCostCentersModificationEvent event) throws Exception;
	StateCostCentersPageEvent requestStateCostCentersFilterPage(RequestStateCostCentersPageEvent event) throws Exception;
	StateCostCentersPageEvent requestStateCostCentersFilter(RequestStateCostCentersPageEvent event) throws Exception;
	
	StateCostCentersPageEvent requestAllByStateCostCenters(EmbeddedField... fields) throws Exception;

	
	
	StateCostCentersDetailsEvent getOnlyRecord() throws Exception;
	
}