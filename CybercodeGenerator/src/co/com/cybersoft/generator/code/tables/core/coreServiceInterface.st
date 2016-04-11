package co.com.cybersoft.$module$.tables.core.services.$entityName$;

import co.com.cybersoft.$module$.tables.events.$entityName$.$createEvent$;
import co.com.cybersoft.$module$.tables.events.$entityName$.$singleResponseEvent$;
import co.com.cybersoft.$module$.tables.events.$entityName$.$allResponseEvent$;
import co.com.cybersoft.$module$.tables.events.$entityName$.$modifyEvent$;
import co.com.cybersoft.$module$.tables.events.$entityName$.$singleRequestEvent$;
import co.com.cybersoft.$module$.tables.events.$entityName$.$allRequestEvent$;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
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
	
	$entityUppercaseName$PageEvent request$entityUppercaseName$FilterPage(Request$entityUppercaseName$PageEvent event) throws Exception;

	$entityUppercaseName$PageEvent request$entityUppercaseName$Filter(Request$entityUppercaseName$PageEvent event) throws Exception;
	
}
