package co.com.cybersoft.tables.persistence.services.$tableName$;

import co.com.cybersoft.tables.persistence.repository.$tableName$.$entityName$Repository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class $entityName$PersistenceFactory {

	$entityName$Repository $tableName$Repository;
	
	public $entityName$PersistenceFactory($entityName$Repository $tableName$Repository){
		this.$tableName$Repository=$tableName$Repository;
	}
	
	public co.com.cybersoft.tables.persistence.domain.$entityName$ get$entityName$ByField(String field, String value){
		$queriesByFields$		
		return null;
	}
}
