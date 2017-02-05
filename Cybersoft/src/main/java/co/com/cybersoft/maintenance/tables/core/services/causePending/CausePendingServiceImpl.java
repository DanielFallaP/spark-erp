package co.com.cybersoft.maintenance.tables.core.services.causePending;

import co.com.cybersoft.maintenance.tables.events.causePending.CreateCausePendingEvent;
import co.com.cybersoft.maintenance.tables.events.causePending.CausePendingDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.causePending.CausePendingPageEvent;
import co.com.cybersoft.maintenance.tables.events.causePending.CausePendingModificationEvent;
import co.com.cybersoft.maintenance.tables.events.causePending.RequestCausePendingDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.causePending.RequestCausePendingPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.causePending.CausePendingPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CausePendingServiceImpl implements CausePendingService{

	private final CausePendingPersistenceService causePendingPersistenceService;
	
	public CausePendingServiceImpl(final CausePendingPersistenceService causePendingPersistenceService){
		this.causePendingPersistenceService=causePendingPersistenceService;
	}
	
	/**
	 */
	public CausePendingDetailsEvent createCausePending(CreateCausePendingEvent event ) throws Exception{
		return causePendingPersistenceService.createCausePending(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public CausePendingPageEvent requestCausePendingPage(RequestCausePendingPageEvent event) throws Exception{
		return causePendingPersistenceService.requestCausePendingPage(event);
	}

	public CausePendingDetailsEvent requestCausePendingDetails(RequestCausePendingDetailsEvent event ) throws Exception{
		return causePendingPersistenceService.requestCausePendingDetails(event);
	}

	public CausePendingDetailsEvent modifyCausePending(CausePendingModificationEvent event) throws Exception {
		return causePendingPersistenceService.modifyCausePending(event);
	}
	
	public CausePendingDetailsEvent requestOnlyRecord() throws Exception {
		return causePendingPersistenceService.getOnlyRecord();
	}
	
	public CausePendingPageEvent requestCausePendingFilterPage(RequestCausePendingPageEvent event) throws Exception {
		return causePendingPersistenceService.requestCausePendingFilterPage(event);
	}
	
	public CausePendingPageEvent requestCausePendingFilter(RequestCausePendingPageEvent event) throws Exception {
		return causePendingPersistenceService.requestCausePendingFilter(event);
	}
	

	public CausePendingPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return causePendingPersistenceService.requestAllByCompany(fields);
	}public CausePendingPageEvent requestAllByNameCausePending(EmbeddedField... fields) throws Exception {
		return causePendingPersistenceService.requestAllByNameCausePending(fields);
	}
	
	
	
}