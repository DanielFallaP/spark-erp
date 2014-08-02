package co.com.cybersoft.docs.persistence.repository.$docName$;

import co.com.cybersoft.docs.persistence.domain.$upperDocName$;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface $upperDocName$CustomRepo {

	$upperDocName$ save($upperDocName$ $docName$) throws Exception;
	
	List<$upperDocName$> findByContainingNumericId(String numericId) throws Exception;
	

}