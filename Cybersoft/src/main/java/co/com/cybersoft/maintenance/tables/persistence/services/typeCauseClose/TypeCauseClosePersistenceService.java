package co.com.cybersoft.maintenance.tables.persistence.services.typeCauseClose;

import co.com.cybersoft.maintenance.tables.events.typeCauseClose.CreateTypeCauseCloseEvent;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.TypeCauseCloseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.TypeCauseClosePageEvent;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.TypeCauseCloseModificationEvent;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.RequestTypeCauseCloseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.RequestTypeCauseClosePageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface TypeCauseClosePersistenceService {

	TypeCauseCloseDetailsEvent createTypeCauseClose(CreateTypeCauseCloseEvent event) throws Exception;

	TypeCauseClosePageEvent requestTypeCauseClosePage(RequestTypeCauseClosePageEvent event) throws Exception;

	TypeCauseCloseDetailsEvent requestTypeCauseCloseDetails(RequestTypeCauseCloseDetailsEvent event) throws Exception;
	
	TypeCauseCloseDetailsEvent modifyTypeCauseClose(TypeCauseCloseModificationEvent event) throws Exception;
	TypeCauseClosePageEvent requestTypeCauseCloseFilterPage(RequestTypeCauseClosePageEvent event) throws Exception;
	TypeCauseClosePageEvent requestTypeCauseCloseFilter(RequestTypeCauseClosePageEvent event) throws Exception;
	
	TypeCauseClosePageEvent requestAllByTypeCauseClose(EmbeddedField... fields) throws Exception;

	
	
	TypeCauseCloseDetailsEvent getOnlyRecord() throws Exception;
	
}