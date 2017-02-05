package co.com.cybersoft.maintenance.tables.core.services.storeType;

import co.com.cybersoft.maintenance.tables.events.storeType.CreateStoreTypeEvent;
import co.com.cybersoft.maintenance.tables.events.storeType.StoreTypeDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.storeType.StoreTypePageEvent;
import co.com.cybersoft.maintenance.tables.events.storeType.StoreTypeModificationEvent;
import co.com.cybersoft.maintenance.tables.events.storeType.RequestStoreTypeDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.storeType.RequestStoreTypePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.storeType.StoreTypePersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class StoreTypeServiceImpl implements StoreTypeService{

	private final StoreTypePersistenceService storeTypePersistenceService;
	
	public StoreTypeServiceImpl(final StoreTypePersistenceService storeTypePersistenceService){
		this.storeTypePersistenceService=storeTypePersistenceService;
	}
	
	/**
	 */
	public StoreTypeDetailsEvent createStoreType(CreateStoreTypeEvent event ) throws Exception{
		return storeTypePersistenceService.createStoreType(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public StoreTypePageEvent requestStoreTypePage(RequestStoreTypePageEvent event) throws Exception{
		return storeTypePersistenceService.requestStoreTypePage(event);
	}

	public StoreTypeDetailsEvent requestStoreTypeDetails(RequestStoreTypeDetailsEvent event ) throws Exception{
		return storeTypePersistenceService.requestStoreTypeDetails(event);
	}

	public StoreTypeDetailsEvent modifyStoreType(StoreTypeModificationEvent event) throws Exception {
		return storeTypePersistenceService.modifyStoreType(event);
	}
	
	public StoreTypeDetailsEvent requestOnlyRecord() throws Exception {
		return storeTypePersistenceService.getOnlyRecord();
	}
	
	public StoreTypePageEvent requestStoreTypeFilterPage(RequestStoreTypePageEvent event) throws Exception {
		return storeTypePersistenceService.requestStoreTypeFilterPage(event);
	}
	
	public StoreTypePageEvent requestStoreTypeFilter(RequestStoreTypePageEvent event) throws Exception {
		return storeTypePersistenceService.requestStoreTypeFilter(event);
	}
	

	public StoreTypePageEvent requestAllByStoreName(EmbeddedField... fields) throws Exception {
		return storeTypePersistenceService.requestAllByStoreName(fields);
	}
	
	
	
}