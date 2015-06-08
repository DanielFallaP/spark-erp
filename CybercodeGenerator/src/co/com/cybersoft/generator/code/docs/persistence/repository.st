package co.com.cybersoft.$module$.docs.persistence.repository.$docName$;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.$module$.docs.persistence.domain.$upperDocName$;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface $upperDocName$Repository extends MongoRepository<$upperDocName$, String>{

	$upperDocName$ findByActive(Boolean value);
	
	$upperDocName$ findByNumericId(Long value);

}