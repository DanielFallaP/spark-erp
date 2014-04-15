package co.com.cybersoft.events.grupo;

import co.com.cybersoft.core.domain.GrupoDetails;

/**
 * Event class for Grupo
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class GrupoDetailsEvent {
	
	private GrupoDetails grupoDetails;
	
	public GrupoDetailsEvent(GrupoDetails grupoDetails){
		this.grupoDetails=grupoDetails;
	}

	public GrupoDetails getGrupoDetails() {
		return grupoDetails;
	}

}