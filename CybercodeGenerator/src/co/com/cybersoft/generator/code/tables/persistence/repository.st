package co.com.cybersoft.tables.persistence.repository.$tableName$;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.tables.persistence.domain.$entityName$;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface $entityName$Repository extends MongoRepository<$entityName$, String>{
	$findByFields$
}