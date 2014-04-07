package co.com.cybersoft.core.services.$entityName$;

import co.com.cybersoft.events.$entityName$.$createEvent$;
import co.com.cybersoft.events.$entityName$.$createdEvent$;
import co.com.cybersoft.events.$entityName$.$singleResponseEvent$;
import co.com.cybersoft.events.$entityName$.$allResponseEvent$;
import co.com.cybersoft.events.$entityName$.$modifyEvent$;
import co.com.cybersoft.events.$entityName$.$singleRequestEvent$;
import co.com.cybersoft.events.$entityName$.$allRequestEvent$;

/**
 * 
 * @author Cybersoft 2014. All rights reserved.
 *
 */
public interface $className$ {
	$createdEvent$ create$entityUppercaseName$($createEvent$ event ) throws Exception;
	
	$allResponseEvent$ requestPage($allRequestEvent$ event) throws Exception;

	$singleResponseEvent$ request$entityUppercaseName$Details($singleRequestEvent$ event ) throws Exception;

	$singleResponseEvent$ modify$entityUppercaseName$($modifyEvent$ event) throws Exception;
}
