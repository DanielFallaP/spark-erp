package co.com.cybersoft.docs.events.$docName$;

import org.springframework.data.domain.Pageable;

/**
 * Event class for $upperDocName$
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class Request$upperDocName$PageEvent {

	private Pageable pageable;
	
	public Request$upperDocName$PageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}