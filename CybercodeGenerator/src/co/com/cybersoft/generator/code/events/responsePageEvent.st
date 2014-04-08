package co.com.cybersoft.events.$tableName$;

import org.springframework.data.domain.Page;

import co.com.cybersoft.persistence.domain.$entityName$;

/**
 * Event class for $entityName$
 * @author Cybersoft 2014. All rights reserved.
 *
 */
public class $entityName$PageEvent {
	private Page<$entityName$> $tableName$Page;
	
	public $entityName$PageEvent(Page<$entityName$>  $tableName$Page){
		this.$tableName$Page= $tableName$Page;
	}

	public Page<$entityName$> get$entityName$Page() {
		return $tableName$Page;
	}
	
}