package co.com.cybersoft.$module$.tables.events.$tableName$;

import co.com.cybersoft.$module$.tables.core.domain.$entityName$Details;

/**
 * Event class for $entityName$
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class $entityName$DetailsEvent {
	
	private $entityName$Details $tableName$Details;
	
	public $entityName$DetailsEvent($entityName$Details $tableName$Details){
		this.$tableName$Details=$tableName$Details;
	}

	public $entityName$Details get$entityName$Details() {
		return $tableName$Details;
	}

}
