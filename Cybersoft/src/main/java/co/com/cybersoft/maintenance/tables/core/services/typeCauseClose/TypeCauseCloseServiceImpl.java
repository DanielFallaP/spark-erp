package co.com.cybersoft.maintenance.tables.core.services.typeCauseClose;

import co.com.cybersoft.maintenance.tables.events.typeCauseClose.CreateTypeCauseCloseEvent;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.TypeCauseCloseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.TypeCauseClosePageEvent;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.TypeCauseCloseModificationEvent;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.RequestTypeCauseCloseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.RequestTypeCauseClosePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.typeCauseClose.TypeCauseClosePersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class TypeCauseCloseServiceImpl implements TypeCauseCloseService{

	private final TypeCauseClosePersistenceService typeCauseClosePersistenceService;
	
	public TypeCauseCloseServiceImpl(final TypeCauseClosePersistenceService typeCauseClosePersistenceService){
		this.typeCauseClosePersistenceService=typeCauseClosePersistenceService;
	}
	
	/**
	 */
	public TypeCauseCloseDetailsEvent createTypeCauseClose(CreateTypeCauseCloseEvent event ) throws Exception{
		return typeCauseClosePersistenceService.createTypeCauseClose(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public TypeCauseClosePageEvent requestTypeCauseClosePage(RequestTypeCauseClosePageEvent event) throws Exception{
		return typeCauseClosePersistenceService.requestTypeCauseClosePage(event);
	}

	public TypeCauseCloseDetailsEvent requestTypeCauseCloseDetails(RequestTypeCauseCloseDetailsEvent event ) throws Exception{
		return typeCauseClosePersistenceService.requestTypeCauseCloseDetails(event);
	}

	public TypeCauseCloseDetailsEvent modifyTypeCauseClose(TypeCauseCloseModificationEvent event) throws Exception {
		return typeCauseClosePersistenceService.modifyTypeCauseClose(event);
	}
	
	public TypeCauseCloseDetailsEvent requestOnlyRecord() throws Exception {
		return typeCauseClosePersistenceService.getOnlyRecord();
	}
	
	public TypeCauseClosePageEvent requestTypeCauseCloseFilterPage(RequestTypeCauseClosePageEvent event) throws Exception {
		return typeCauseClosePersistenceService.requestTypeCauseCloseFilterPage(event);
	}
	
	public TypeCauseClosePageEvent requestTypeCauseCloseFilter(RequestTypeCauseClosePageEvent event) throws Exception {
		return typeCauseClosePersistenceService.requestTypeCauseCloseFilter(event);
	}
	

	public TypeCauseClosePageEvent requestAllByTypeCauseClose(EmbeddedField... fields) throws Exception {
		return typeCauseClosePersistenceService.requestAllByTypeCauseClose(fields);
	}
	
	
	
}