package co.com.cybersoft.events.grupo;

/**
 * Event class for Grupo
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestGrupoDetailsEvent {

	private String id;
	
	public RequestGrupoDetailsEvent(String id){
		this.id=id;
	}

	public String getId() {
		return id;
	}
}