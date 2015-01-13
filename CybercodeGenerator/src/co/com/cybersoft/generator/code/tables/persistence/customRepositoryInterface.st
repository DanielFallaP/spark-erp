package co.com.cybersoft.tables.persistence.repository.$tableName$;

import java.util.List;

import co.com.cybersoft.tables.persistence.domain.$entityName$;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.tables.web.domain.$tableName$.$entityName$FilterInfo;


/**
 * 
 * @author Cybersystems 2015. All rights reserved.
 *
 */
public interface $entityName$CustomRepo {

	$autocompleteQuery$

	$findAllActive$
	
	Page<$entityName$> findAll(Pageable pageable, $entityName$FilterInfo filter)throws Exception;
	
}
