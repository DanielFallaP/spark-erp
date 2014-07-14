package co.com.cybersoft.docs.persistence.services.$docName$;

import java.util.List;

import co.com.cybersoft.docs.events.$docName$.Request$upperDocName$Event;
import co.com.cybersoft.docs.events.$docName$.Request$upperDocName$PageEvent;
import co.com.cybersoft.docs.events.$docName$.$upperDocName$Event;
import co.com.cybersoft.docs.events.$docName$.$upperDocName$ModificationEvent;
import co.com.cybersoft.docs.events.$docName$.$upperDocName$PageEvent;
import co.com.cybersoft.docs.events.$docName$.Save$upperDocName$Event;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface $upperDocName$PersistenceService {
	
	$upperDocName$Event save$upperDocName$(Save$upperDocName$Event event) throws Exception;
	
	$upperDocName$Event save$upperDocName$Body(Save$upperDocName$Event event) throws Exception;
	
	$upperDocName$Event update$upperDocName$Body(Save$upperDocName$Event event) throws Exception;

	$upperDocName$Event create$upperDocName$(Save$upperDocName$Event event) throws Exception;
	
	$upperDocName$Event delete$upperDocName$(Save$upperDocName$Event event, List<String> toDelete) throws Exception;

	$upperDocName$PageEvent request$upperDocName$Page(Request$upperDocName$PageEvent event) throws Exception;

	$upperDocName$Event request$upperDocName$Details(Request$upperDocName$Event event) throws Exception;
	
	
	
}