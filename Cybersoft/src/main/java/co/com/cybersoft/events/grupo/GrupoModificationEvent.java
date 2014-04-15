package co.com.cybersoft.events.grupo;

import co.com.cybersoft.core.domain.GrupoDetails;

/**
 * Event class for Grupo
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class GrupoModificationEvent {

	private GrupoDetails grupoDetails;
	
	public GrupoModificationEvent(GrupoDetails grupoDetails){
		this.grupoDetails=grupoDetails;
	}

	public GrupoDetails getGrupoDetails() {
		return grupoDetails;
	}
	
}