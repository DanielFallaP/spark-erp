package co.com.cybersoft.maintenance.tables.core.services.typeWork;

import co.com.cybersoft.maintenance.tables.events.typeWork.CreateTypeWorkEvent;
import co.com.cybersoft.maintenance.tables.events.typeWork.TypeWorkDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeWork.TypeWorkPageEvent;
import co.com.cybersoft.maintenance.tables.events.typeWork.TypeWorkModificationEvent;
import co.com.cybersoft.maintenance.tables.events.typeWork.RequestTypeWorkDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeWork.RequestTypeWorkPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.typeWork.TypeWorkPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class TypeWorkServiceImpl implements TypeWorkService{

	private final TypeWorkPersistenceService typeWorkPersistenceService;
	
	public TypeWorkServiceImpl(final TypeWorkPersistenceService typeWorkPersistenceService){
		this.typeWorkPersistenceService=typeWorkPersistenceService;
	}
	
	/**
	 */
	public TypeWorkDetailsEvent createTypeWork(CreateTypeWorkEvent event ) throws Exception{
		return typeWorkPersistenceService.createTypeWork(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public TypeWorkPageEvent requestTypeWorkPage(RequestTypeWorkPageEvent event) throws Exception{
		return typeWorkPersistenceService.requestTypeWorkPage(event);
	}

	public TypeWorkDetailsEvent requestTypeWorkDetails(RequestTypeWorkDetailsEvent event ) throws Exception{
		return typeWorkPersistenceService.requestTypeWorkDetails(event);
	}

	public TypeWorkDetailsEvent modifyTypeWork(TypeWorkModificationEvent event) throws Exception {
		return typeWorkPersistenceService.modifyTypeWork(event);
	}
	
	public TypeWorkDetailsEvent requestOnlyRecord() throws Exception {
		return typeWorkPersistenceService.getOnlyRecord();
	}
	
	public TypeWorkPageEvent requestTypeWorkFilterPage(RequestTypeWorkPageEvent event) throws Exception {
		return typeWorkPersistenceService.requestTypeWorkFilterPage(event);
	}
	
	public TypeWorkPageEvent requestTypeWorkFilter(RequestTypeWorkPageEvent event) throws Exception {
		return typeWorkPersistenceService.requestTypeWorkFilter(event);
	}
	

	public TypeWorkPageEvent requestAllByTypeWork(EmbeddedField... fields) throws Exception {
		return typeWorkPersistenceService.requestAllByTypeWork(fields);
	}
	
	
	
}