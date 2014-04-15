package co.com.cybersoft.events.unidadMedida;

/**
 * Event class for UnidadMedida
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestUnidadMedidaDetailsEvent {

	private String id;
	
	public RequestUnidadMedidaDetailsEvent(String id){
		this.id=id;
	}

	public String getId() {
		return id;
	}
}