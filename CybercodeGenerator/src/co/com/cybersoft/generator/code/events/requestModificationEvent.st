package co.com.cybersoft.events.$tableName$;

import co.com.cybersoft.core.domain.$entityName$Details;

/**
 * Event class for $entityName$
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class $entityName$ModificationEvent {

	private $entityName$Details $tableName$Details;
	
	public $entityName$ModificationEvent($entityName$Details $tableName$Details){
		this.$tableName$Details=$tableName$Details;
	}

	public $entityName$Details get$entityName$Details() {
		return $tableName$Details;
	}
	
}