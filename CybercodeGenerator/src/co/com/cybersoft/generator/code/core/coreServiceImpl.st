package co.com.cybersoft.core.services.$entityName$;

import java.util.Date;

import co.com.cybersoft.events.$entityName$.$createEvent$;
import co.com.cybersoft.events.$entityName$.$singleResponseEvent$;
import co.com.cybersoft.events.$entityName$.$allResponseEvent$;
import co.com.cybersoft.events.$entityName$.$modifyEvent$;
import co.com.cybersoft.events.$entityName$.$singleRequestEvent$;
import co.com.cybersoft.events.$entityName$.$allRequestEvent$;
import co.com.cybersoft.persistence.services.$entityName$.$entityUppercaseName$PersistenceService;

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
		event.get$entityUppercaseName$Details().setDateOfModification(new Date());
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
		event.get$entityUppercaseName$Details().setDateOfModification(new Date());
		return $entityName$PersistenceService.modify$entityUppercaseName$(event);
	}

}
