package co.com.cybersoft.core.services.$tableName$;

import java.util.Date;

import co.com.cybersoft.events.$tableName$.$createEvent$;
import co.com.cybersoft.events.$tableName$.$createdEvent$;
import co.com.cybersoft.events.$tableName$.$singleResponseEvent$;
import co.com.cybersoft.events.$tableName$.$allResponseEvent$;
import co.com.cybersoft.events.$tableName$.$modifyEvent$;
import co.com.cybersoft.events.$tableName$.$singleRequestEvent$;
import co.com.cybersoft.events.$tableName$.$allRequestEvent$;

/**
 * 
 * @author Cybersoft 2014. All rights reserved.
 *
 */
public class $entityName$ServiceImpl implements $entityName$Service{

	private final $entityName$PersistenceService $entityName$PersistenceService;
	
	public $entityName$ServiceImpl(final $entityName$PersistenceService $tableName$PersistenceService){
		this.itemPersistenceService=itemPersistenceService;
	}
	
	/**
	 */
	@Override
	$createdEvent$ create$entityUppercaseName$($createEvent$ event ) throws Exception{
		event.getDetails().setDateOfCreation(new Date());
		return $entityName$PersistenceService.create$entityUppercaseName$(createItemEvent);
	}

	/**
	 * Returns all the items of the page requested
	 */
	@Override
	$allResponseEvent$ requestPage($allRequestEvent$ event) throws Exception{
		return $entityName$PersistenceService.requestPage(event);
	}

	@Override
	$singleResponseEvent$ request$entityUppercaseName$Details($singleRequestEvent$ event ) throws Exception{
		return $entityName$PersistenceService.request$entityUppercaseName$Details(event);
	}

	@Override
	$singleResponseEvent$ modify$entityUppercaseName$($modifyEvent$ event) throws Exception {
		return $entityName$PersistenceService.modify$entityUppercaseName$(event);
	}

}
