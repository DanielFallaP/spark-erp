package co.com.cybersoft.maintenance.tables.core.services.typeWork;

import co.com.cybersoft.maintenance.tables.events.typeWork.CreateTypeWorkEvent;
import co.com.cybersoft.maintenance.tables.events.typeWork.TypeWorkDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeWork.TypeWorkPageEvent;
import co.com.cybersoft.maintenance.tables.events.typeWork.TypeWorkModificationEvent;
import co.com.cybersoft.maintenance.tables.events.typeWork.RequestTypeWorkDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeWork.RequestTypeWorkPageEvent;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface TypeWorkService {
	TypeWorkDetailsEvent requestOnlyRecord() throws Exception;

	TypeWorkDetailsEvent createTypeWork(CreateTypeWorkEvent event ) throws Exception;
	
	TypeWorkPageEvent requestTypeWorkPage(RequestTypeWorkPageEvent event) throws Exception;

	TypeWorkDetailsEvent requestTypeWorkDetails(RequestTypeWorkDetailsEvent event ) throws Exception;

	TypeWorkDetailsEvent modifyTypeWork(TypeWorkModificationEvent event) throws Exception;
	
	TypeWorkPageEvent requestAllByTypeWork(EmbeddedField... fields) throws Exception;

	
	
	TypeWorkPageEvent requestTypeWorkFilterPage(RequestTypeWorkPageEvent event) throws Exception;

	TypeWorkPageEvent requestTypeWorkFilter(RequestTypeWorkPageEvent event) throws Exception;
	
}