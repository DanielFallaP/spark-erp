package co.com.cybersoft.events.grupo;

import org.springframework.data.domain.Pageable;

/**
 * Event class for Grupo
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestGrupoPageEvent {

	private Pageable pageable;
	
	public RequestGrupoPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
}