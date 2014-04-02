package co.com.cybersoft.events.items;

/**
 * 
 * @author Daniel Falla
 *
 */
public class RequestItemDetailsEvent {

	private String id;
	
	public RequestItemDetailsEvent(String id){
		this.id=id;
	}

	public String getId() {
		return id;
	}
}
