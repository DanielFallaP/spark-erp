package co.com.cybersoft.docs.events.$docName$;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.docs.web.domain.$docName$.$upperDocName$FilterInfo;

/**
 * Event class for $upperDocName$
 * @author Cybersystems 2015. All rights reserved.
 *
 */
public class Request$upperDocName$PageEvent {

	private Pageable pageable;
	private $upperDocName$FilterInfo filter;
	
	public Request$upperDocName$PageEvent(Pageable pageable, $upperDocName$FilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public Request$upperDocName$PageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public $upperDocName$FilterInfo getFilter() {
		return filter;
	}
}