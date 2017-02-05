package co.com.cybersoft.maintenance.tables.core.services.causeClose;

import co.com.cybersoft.maintenance.tables.events.causeClose.CreateCauseCloseEvent;
import co.com.cybersoft.maintenance.tables.events.causeClose.CauseCloseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.causeClose.CauseClosePageEvent;
import co.com.cybersoft.maintenance.tables.events.causeClose.CauseCloseModificationEvent;
import co.com.cybersoft.maintenance.tables.events.causeClose.RequestCauseCloseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.causeClose.RequestCauseClosePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.causeClose.CauseClosePersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CauseCloseServiceImpl implements CauseCloseService{

	private final CauseClosePersistenceService causeClosePersistenceService;
	
	public CauseCloseServiceImpl(final CauseClosePersistenceService causeClosePersistenceService){
		this.causeClosePersistenceService=causeClosePersistenceService;
	}
	
	/**
	 */
	public CauseCloseDetailsEvent createCauseClose(CreateCauseCloseEvent event ) throws Exception{
		return causeClosePersistenceService.createCauseClose(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public CauseClosePageEvent requestCauseClosePage(RequestCauseClosePageEvent event) throws Exception{
		return causeClosePersistenceService.requestCauseClosePage(event);
	}

	public CauseCloseDetailsEvent requestCauseCloseDetails(RequestCauseCloseDetailsEvent event ) throws Exception{
		return causeClosePersistenceService.requestCauseCloseDetails(event);
	}

	public CauseCloseDetailsEvent modifyCauseClose(CauseCloseModificationEvent event) throws Exception {
		return causeClosePersistenceService.modifyCauseClose(event);
	}
	
	public CauseCloseDetailsEvent requestOnlyRecord() throws Exception {
		return causeClosePersistenceService.getOnlyRecord();
	}
	
	public CauseClosePageEvent requestCauseCloseFilterPage(RequestCauseClosePageEvent event) throws Exception {
		return causeClosePersistenceService.requestCauseCloseFilterPage(event);
	}
	
	public CauseClosePageEvent requestCauseCloseFilter(RequestCauseClosePageEvent event) throws Exception {
		return causeClosePersistenceService.requestCauseCloseFilter(event);
	}
	

	public CauseClosePageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return causeClosePersistenceService.requestAllByCompany(fields);
	}public CauseClosePageEvent requestAllByNameCauseClose(EmbeddedField... fields) throws Exception {
		return causeClosePersistenceService.requestAllByNameCauseClose(fields);
	}public CauseClosePageEvent requestAllByTypeCauseClose(EmbeddedField... fields) throws Exception {
		return causeClosePersistenceService.requestAllByTypeCauseClose(fields);
	}
	
	
	
}