package co.com.cybersoft.events.$tableName$;

import org.springframework.data.domain.Pageable;

/**
 * Event class for $entityName$
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class Request$entityName$PageEvent {

	private Pageable pageable;
	
	public Request$entityName$PageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}