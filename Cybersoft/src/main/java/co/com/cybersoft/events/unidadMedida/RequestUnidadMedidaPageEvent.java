package co.com.cybersoft.events.unidadMedida;

import org.springframework.data.domain.Pageable;

/**
 * Event class for UnidadMedida
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestUnidadMedidaPageEvent {

	private Pageable pageable;
	
	public RequestUnidadMedidaPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}