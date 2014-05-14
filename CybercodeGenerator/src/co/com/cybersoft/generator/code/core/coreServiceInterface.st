package co.com.cybersoft.core.services.$entityName$;

import co.com.cybersoft.events.$entityName$.$createEvent$;
import co.com.cybersoft.events.$entityName$.$singleResponseEvent$;
import co.com.cybersoft.events.$entityName$.$allResponseEvent$;
import co.com.cybersoft.events.$entityName$.$modifyEvent$;
import co.com.cybersoft.events.$entityName$.$singleRequestEvent$;
import co.com.cybersoft.events.$entityName$.$allRequestEvent$;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface $className$ {
	$upperEntityName$DetailsEvent requestOnlyRecord() throws Exception;

	$singleResponseEvent$ create$entityUppercaseName$($createEvent$ event ) throws Exception;
	
	$allResponseEvent$ request$entityUppercaseName$Page($allRequestEvent$ event) throws Exception;

	$singleResponseEvent$ request$entityUppercaseName$Details($singleRequestEvent$ event ) throws Exception;

	$singleResponseEvent$ modify$entityUppercaseName$($modifyEvent$ event) throws Exception;
	
	$requestAll$
	
	$autocompleteRequest$
	
	
}
