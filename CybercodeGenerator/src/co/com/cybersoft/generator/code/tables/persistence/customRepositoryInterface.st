package co.com.cybersoft.$module$.tables.persistence.repository.$tableName$;

import java.util.List;

import co.com.cybersoft.$module$.tables.persistence.domain.$entityName$;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.$module$.tables.web.domain.$tableName$.$entityName$FilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface $entityName$CustomRepo {

	$autocompleteQuery$

	$findAllActive$
	
	Page<$entityName$> findAll(Pageable pageable, $entityName$FilterInfo filter)throws Exception;

	List<$entityName$> findAllNoPage(Pageable pageable, $entityName$FilterInfo filter)throws Exception;
	
}
