package co.com.cybersoft.tables.events.$tableName$;

import co.com.cybersoft.tables.core.domain.$entityName$Details;

/**
 * Event class for $entityName$
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class Create$entityName$Event {
		
	private $entityName$Details $tableName$Details;
	
	public Create$entityName$Event($entityName$Details $tableName$Details){
		this.$tableName$Details=$tableName$Details;
	}

	public $entityName$Details get$entityName$Details() {
		return $tableName$Details;
	}
	
	
}
