package co.com.cybersoft.$module$.tables.persistence.services.$tableName$;

import co.com.cybersoft.$module$.tables.events.$tableName$.Create$entityName$Event;
import co.com.cybersoft.$module$.tables.events.$tableName$.$entityName$DetailsEvent;
import co.com.cybersoft.$module$.tables.events.$tableName$.$entityName$PageEvent;
import co.com.cybersoft.$module$.tables.events.$tableName$.$entityName$ModificationEvent;
import co.com.cybersoft.$module$.tables.events.$tableName$.Request$entityName$DetailsEvent;
import co.com.cybersoft.$module$.tables.events.$tableName$.Request$entityName$PageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2015. All rights reserved.
 *
 */
public interface $entityName$PersistenceService {

	$entityName$DetailsEvent create$entityName$(Create$entityName$Event event) throws Exception;

	$entityName$PageEvent request$entityName$Page(Request$entityName$PageEvent event) throws Exception;

	$entityName$DetailsEvent request$entityName$Details(Request$entityName$DetailsEvent event) throws Exception;
	
	$entityName$DetailsEvent modify$entityName$($entityName$ModificationEvent event) throws Exception;
	$entityName$PageEvent request$entityName$FilterPage(Request$entityName$PageEvent event) throws Exception;
	$entityName$PageEvent request$entityName$Filter(Request$entityName$PageEvent event) throws Exception;
	
	$requestAll$
	
	$autocompleteRequest$
	
	$entityName$DetailsEvent getOnlyRecord() throws Exception;
	
}
