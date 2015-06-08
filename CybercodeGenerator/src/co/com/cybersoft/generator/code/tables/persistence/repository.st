package co.com.cybersoft.$module$.tables.persistence.repository.$tableName$;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.$module$.tables.persistence.domain.$entityName$;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface $entityName$Repository extends MongoRepository<$entityName$, String>{
	$findByFields$
}
