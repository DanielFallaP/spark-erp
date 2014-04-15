package co.com.cybersoft.events.articulo;

import co.com.cybersoft.core.domain.ArticuloDetails;

/**
 * Event class for Articulo
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateArticuloEvent {
		
	private ArticuloDetails articuloDetails;
	
	public CreateArticuloEvent(ArticuloDetails articuloDetails){
		this.articuloDetails=articuloDetails;
	}

	public ArticuloDetails getArticuloDetails() {
		return articuloDetails;
	}
	
	
}