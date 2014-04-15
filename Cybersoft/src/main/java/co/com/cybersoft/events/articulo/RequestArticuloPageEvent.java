package co.com.cybersoft.events.articulo;

import org.springframework.data.domain.Pageable;

/**
 * Event class for Articulo
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestArticuloPageEvent {

	private Pageable pageable;
	
	public RequestArticuloPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}