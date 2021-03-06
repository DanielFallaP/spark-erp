package co.com.cybersoft.$module$.tables.events.$tableName$;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.$module$.tables.web.domain.$tableName$.$entityName$FilterInfo;

/**
 * Event class for $entityName$
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class Request$entityName$PageEvent {

	private Pageable pageable;
	private $entityName$FilterInfo filter;
	
	public Request$entityName$PageEvent(Pageable pageable, $entityName$FilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public Request$entityName$PageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public $entityName$FilterInfo getFilter() {
		return filter;
	}
}