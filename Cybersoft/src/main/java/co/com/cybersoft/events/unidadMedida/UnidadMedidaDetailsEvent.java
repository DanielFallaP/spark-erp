package co.com.cybersoft.events.unidadMedida;

import co.com.cybersoft.core.domain.UnidadMedidaDetails;

/**
 * Event class for UnidadMedida
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class UnidadMedidaDetailsEvent {
	
	private UnidadMedidaDetails unidadMedidaDetails;
	
	public UnidadMedidaDetailsEvent(UnidadMedidaDetails unidadMedidaDetails){
		this.unidadMedidaDetails=unidadMedidaDetails;
	}

	public UnidadMedidaDetails getUnidadMedidaDetails() {
		return unidadMedidaDetails;
	}

}