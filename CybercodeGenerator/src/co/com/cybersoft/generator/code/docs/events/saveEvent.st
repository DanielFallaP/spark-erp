package co.com.cybersoft.docs.events.$docName$;

import co.com.cybersoft.docs.web.domain.$docName$.$upperDocName$Info;



/**
 * Event class for $upperDocName$Info
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class Save$upperDocName$Event {
		
	private $upperDocName$Info $docName$Info;
	
	public Save$upperDocName$Event($upperDocName$Info $docName$Info){
		this.$docName$Info=$docName$Info;
	}

	public $upperDocName$Info get$upperDocName$Info() {
		return $docName$Info;
	}
	
	
}