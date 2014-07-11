package co.com.cybersoft.tables.core.services.$entityName$;

import co.com.cybersoft.tables.events.$entityName$.$createEvent$;
import co.com.cybersoft.tables.events.$entityName$.$singleResponseEvent$;
import co.com.cybersoft.tables.events.$entityName$.$allResponseEvent$;
import co.com.cybersoft.tables.events.$entityName$.$modifyEvent$;
import co.com.cybersoft.tables.events.$entityName$.$singleRequestEvent$;
import co.com.cybersoft.tables.events.$entityName$.$allRequestEvent$;
import co.com.cybersoft.tables.persistence.services.$entityName$.$entityUppercaseName$PersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class $entityUppercaseName$ServiceImpl implements $entityUppercaseName$Service{

	private final $entityUppercaseName$PersistenceService $entityName$PersistenceService;
	
	public $entityUppercaseName$ServiceImpl(final $entityUppercaseName$PersistenceService $entityName$PersistenceService){
		this.$entityName$PersistenceService=$entityName$PersistenceService;
	}
	
	/**
	 */
	@Override
	public $singleResponseEvent$ create$entityUppercaseName$($createEvent$ event ) throws Exception{
		return $entityName$PersistenceService.create$entityUppercaseName$(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	@Override
	public $allResponseEvent$ request$entityUppercaseName$Page($allRequestEvent$ event) throws Exception{
		return $entityName$PersistenceService.request$entityUppercaseName$Page(event);
	}

	@Override
	public $singleResponseEvent$ request$entityUppercaseName$Details($singleRequestEvent$ event ) throws Exception{
		return $entityName$PersistenceService.request$entityUppercaseName$Details(event);
	}

	@Override
	public $singleResponseEvent$ modify$entityUppercaseName$($modifyEvent$ event) throws Exception {
		return $entityName$PersistenceService.modify$entityUppercaseName$(event);
	}
	
	@Override
	public $upperEntityName$DetailsEvent requestOnlyRecord() throws Exception {
		return $entityName$PersistenceService.getOnlyRecord();
	}

	$requestAll$
	
	$autocompleteRequest$
	
	
}
