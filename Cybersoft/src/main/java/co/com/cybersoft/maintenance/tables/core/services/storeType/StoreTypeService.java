package co.com.cybersoft.maintenance.tables.core.services.storeType;

import co.com.cybersoft.maintenance.tables.events.storeType.CreateStoreTypeEvent;
import co.com.cybersoft.maintenance.tables.events.storeType.StoreTypeDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.storeType.StoreTypePageEvent;
import co.com.cybersoft.maintenance.tables.events.storeType.StoreTypeModificationEvent;
import co.com.cybersoft.maintenance.tables.events.storeType.RequestStoreTypeDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.storeType.RequestStoreTypePageEvent;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface StoreTypeService {
	StoreTypeDetailsEvent requestOnlyRecord() throws Exception;

	StoreTypeDetailsEvent createStoreType(CreateStoreTypeEvent event ) throws Exception;
	
	StoreTypePageEvent requestStoreTypePage(RequestStoreTypePageEvent event) throws Exception;

	StoreTypeDetailsEvent requestStoreTypeDetails(RequestStoreTypeDetailsEvent event ) throws Exception;

	StoreTypeDetailsEvent modifyStoreType(StoreTypeModificationEvent event) throws Exception;
	
	StoreTypePageEvent requestAllByStoreName(EmbeddedField... fields) throws Exception;

	
	
	StoreTypePageEvent requestStoreTypeFilterPage(RequestStoreTypePageEvent event) throws Exception;

	StoreTypePageEvent requestStoreTypeFilter(RequestStoreTypePageEvent event) throws Exception;
	
}