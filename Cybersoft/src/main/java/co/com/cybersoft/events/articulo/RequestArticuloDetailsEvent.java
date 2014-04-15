package co.com.cybersoft.events.articulo;

/**
 * Event class for Articulo
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestArticuloDetailsEvent {

	private String id;
	
	public RequestArticuloDetailsEvent(String id){
		this.id=id;
	}

	public String getId() {
		return id;
	}
}