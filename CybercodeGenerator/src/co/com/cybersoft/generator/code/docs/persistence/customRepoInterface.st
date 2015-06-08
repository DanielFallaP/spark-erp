package co.com.cybersoft.$module$.docs.persistence.repository.$docName$;

import co.com.cybersoft.$module$.docs.persistence.domain.$upperDocName$;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.$module$.docs.web.domain.$docName$.$upperDocName$FilterInfo;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface $upperDocName$CustomRepo {

	$upperDocName$ save($upperDocName$ $docName$) throws Exception;
	
	List<$upperDocName$> findByContainingNumericId(String numericId) throws Exception;
	
	Page<$upperDocName$> findAll(Pageable pageable, $upperDocName$FilterInfo filter)throws Exception;
}